package controller.FileHandler;


import adressbook.model.ABModel;
import controller.PictureController;
import model.Model;
import model.elements.ArtPieceEntry;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Iterator;

/**
 * Klasse die die Operationen auf Datein realisiert
 */

public class FileHandler {
    private final String PATH_SETTINGS_FILE  = "../settings.wv";
    private String pictureFolder;
    private String bitmapFolder;
    private String saveFile;


    /**
     * Konstruktor
     * Lädt bei Initialisierung Informationen aus dem Sekundärspeicher
     * @throws MalformedURLException
     */
    public FileHandler() {
        try {
            initialise();
        } catch (Exception e) {

            createSettingsFileAndEmptySaveFile();
        }
    }



    public void initialise(){
        try {
            parseInitialSettings(new File(PATH_SETTINGS_FILE));
        } catch (IOException e){
            initialiseNewPathSettingsFile();
        } catch (VersionControllException e2) {
            e2.printStackTrace();
        }
    }

    private void createSettingsFileAndEmptySaveFile() {
        //TODO erschaffe neue Datei mit Standardinformationen, erschaffe neuen leeren SaveFile und lege Ordner für Bilder desselben an.
        try {
            File file = new File(PATH_SETTINGS_FILE);
            file.createNewFile();
            createInitialSaveFile();
            initialiseNewPathSettingsFile();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void createInitialSaveFile() throws IOException {
        File saveFile = initialiseFolders();
        saveFile.createNewFile();
        writeEmptyModelToSaveFile();

    }

    private File initialiseFolders() {
        String folderPath = "../profile";
        File folder = new File(folderPath);
        folder.mkdir();
        File bitmapFolder = new File(folderPath + "/bitmaps");
        bitmapFolder.mkdir();
        this.bitmapFolder = bitmapFolder.getPath();
        File pictureFolder = new File(folderPath + "/pictures");
        pictureFolder.mkdir();
        this.pictureFolder = pictureFolder.getPath();
        File saveFile = new File(folderPath + "profile.wz");
        this.saveFile = saveFile.getAbsolutePath();
        return saveFile;
    }

    private void writeEmptyModelToSaveFile() throws IOException {
        Model model = new Model(new ABModel());
        save(model);
    }

    public void relinkPictures(ArtPieceEntry artPieceEntry, String picturePath) throws IOException {
        Image picture = PictureController.loadImage(picturePath);
        Image bitmap = PictureController.createBitmap(picture, 150, 150);
        String pictureFilename = this.pictureFolder + artPieceEntry.getId() + ".jpg";
        String bitmapFilename = this.bitmapFolder + artPieceEntry.getId() + ".jpg";
        PictureController.saveImage(picture, pictureFilename,  1.0f);
        PictureController.saveImage(bitmap, bitmapFilename, 1.0f);
    }

    private void initialiseNewPathSettingsFile()  {
        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_SETTINGS_FILE);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);

            streamWriter.write("1.0\n");
            streamWriter.write(saveFile + "\n");
            streamWriter.write(pictureFolder + "\n");
            streamWriter.write(bitmapFolder + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseInitialSettings(File path_settings_file) throws IOException, VersionControllException {
        FileInputStream fileInputStream = new FileInputStream(path_settings_file);
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        Iterator lineIterator = reader.lines().iterator();

        if(lineIterator.next() == "1.0"){
            saveFile = ((String) lineIterator.next()).trim();
            pictureFolder = ((String) lineIterator.next()).trim();
            bitmapFolder = ((String) lineIterator.next()).trim();
        } else {
            throw new VersionControllException();
        }
        fileInputStream.close();
    }

    public void save(Model model) throws IOException {
        Save save = new Save(model);
        FileOutputStream fos = new FileOutputStream(String.valueOf(saveFile));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(save);
        fos.close();
    }

    public Model load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(saveFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Save save = (Save) ois.readObject();
        fis.close();

        return createModelFromSave(save);
    }

    private Model createModelFromSave(Save save) throws IOException {
        Model model = new Model(save.adressbook);
        createArtpieceEntries(save, model);
        return model;
        }

    private void createArtpieceEntries(Save save, Model model) throws IOException {
        for (ArtpieceSave artpiece : save.artpieces) {
            model.getPieces().add(
                    new ArtPieceEntry(artpiece.getId(), artpiece, loadBitmap(artpiece.getId())));
        }
    }

    private Image loadBitmap(int id) throws IOException {
        return PictureController.loadImage(this.bitmapFolder + id + ".jpg");
    }

    public Image loadHighQualityPicture(int id) throws IOException {
        return PictureController.loadImage(this.pictureFolder + id + ".jpg");
    }
}


