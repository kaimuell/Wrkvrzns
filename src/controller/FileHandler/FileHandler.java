package controller.FileHandler;


import adressbook.model.ABModel;
import tools.PictureTools;
import model.Model;
import model.elements.ArtPieceEntry;

import java.awt.*;
import java.io.*;
import java.util.Iterator;

import static java.lang.Thread.sleep;

/**
 * Klasse die die Operationen auf Datein realisiert
 */

public class FileHandler {
    private final String PATH_SETTINGS_FILE  = "./settings.wvs";
    private String pictureFolder;
    private String bitmapFolder;
    private String saveFile;
    private boolean isInitialised;


    /**
     * Konstruktor
     * Lädt bei Initialisierung Informationen aus dem Sekundärspeicher
     */
    public FileHandler() {
        isInitialised = false;
        try {
            initialise();
            System.out.println("saveFile = "+ saveFile);
            System.out.println("bitmapFolder = "+ bitmapFolder);
            System.out.println("pictureFolder = "+ pictureFolder);
        } catch (Exception e) {
            System.out.println("FileHAndler: erzeuge neue init File");
            createSettingsFileAndEmptySaveFile();
            initialiseNewPathSettingsFile();
        }
        System.out.println("FilHandler initialisiert");
        isInitialised = true;
    }



    private synchronized void initialise()throws IOException{
        System.out.println("FileHandler : initialisiere");
        try {
            parseInitialSettings(new File(PATH_SETTINGS_FILE));
        } catch (VersionControllException e2) {
            e2.printStackTrace();
        }
    }

    private void createSettingsFileAndEmptySaveFile() {
        System.out.println("FileHandler : lege neue Dateien an");
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
        System.out.println("FileHandler : lege SaveFile an");
        File saveFile = initialiseFolders();
        saveFile.createNewFile();
        writeEmptyModelToSaveFile();

    }

    private File initialiseFolders() {
        System.out.println("FileHandler : lege Ordner an");
        String folderPath = "./profiles";
        File folder = new File(folderPath);
        folder.mkdir();
        File bitmapFolder = new File(folderPath + "/bitmaps");
        bitmapFolder.mkdir();
        this.bitmapFolder = bitmapFolder.getPath();
        File pictureFolder = new File(folderPath + "/pictures");
        pictureFolder.mkdir();
        this.pictureFolder = pictureFolder.getPath();
        File saveFile = new File(folderPath + "/profile.wz");
        this.saveFile = saveFile.getPath();
        return saveFile;
    }

    private void writeEmptyModelToSaveFile() throws IOException {
        System.out.println("FileHandler: lege leeres Model an");
        Model model = new Model(new ABModel());
        save(model);
    }

    /**
     * Speichert das Bild und ein Bitmap desselben in den Dateien der Zugewiesenen id des ArtpieceEntry
     * @param artPieceEntryID die Id des Eintrags
     * @param picture das Bild
     * @return
     */
    public synchronized void saveCopyOfPictureLinkedToArtpiece(int artPieceEntryID, Image picture)  {
            try {
                Image bitmap = PictureTools.createBitmap(picture, 150, 150);
                System.out.println("FileHandler : schreibe Bild nach : " + this.pictureFolder + artPieceEntryID + ".jpg");
                System.out.println("FileHandler : schreibe Bitmap nach : " + this.bitmapFolder + artPieceEntryID + ".jpg");
                String pictureFilename = this.pictureFolder + "/" + artPieceEntryID + ".jpg";
                String bitmapFilename = this.bitmapFolder + "/" + artPieceEntryID + ".jpg";
                PictureTools.saveImage(picture, new File(pictureFilename), 1.0f);
                PictureTools.saveImage(bitmap, new File(bitmapFilename), 1.0f);
            }catch (IOException e){
                e.printStackTrace();
            }
    }

    private void initialiseNewPathSettingsFile()  {
        System.out.println("FileHandler : lege neues PathSettingsFile an");
        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_SETTINGS_FILE);
            BufferedWriter streamWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            System.out.println("Schreibe Version Controll Nummer");
            streamWriter.write("1.0\n");
            System.out.println("Schreibe saveFile");
            streamWriter.write(saveFile + "\n");
            System.out.println("Schreibe pictureFolder");
            streamWriter.write(pictureFolder + "\n");
            System.out.println("Schreibe bitmapFolder");
            streamWriter.write(bitmapFolder + "\n");
            streamWriter.flush();
            streamWriter.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseInitialSettings(File path_settings_file) throws IOException, VersionControllException {
        System.out.println("FileHandler : lese OrdnerInformationen in PathSettingsFile");
        FileInputStream fileInputStream = new FileInputStream(path_settings_file);
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        Iterator lineIterator = reader.lines().iterator();
        System.out.println("Lese Zeile 1 : " + lineIterator.hasNext());
        if(lineIterator.next().toString().trim().equals("1.0")){
            System.out.println("Lese Zeile 2");
            saveFile = ((String) lineIterator.next()).trim();
            System.out.println("Lese Zeile 3");
            pictureFolder = ((String) lineIterator.next()).trim();
            System.out.println("Lese Zeile 4");
            bitmapFolder = ((String) lineIterator.next()).trim();
            System.out.println("Lese Zeile 5");
        } else {
            System.out.println("Falsche Version");
            throw new VersionControllException();
        }
        reader.close();
        streamReader.close();
        fileInputStream.close();
    }

    public void save(Model model) throws IOException {
        System.out.println("FileHandler : speichere");
        saveAsString(model);
    }

    private void saveAsString(Model model) throws IOException {
        FileOutputStream fos = new FileOutputStream(saveFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter writer = new BufferedWriter(osw);
        String fileInfo = SaveFileParser.parseFileOutput(model);
        writer.write(fileInfo);
        writer.close();
        osw.close();
        fos.close();
    }

    private void saveAsBinary(Model model) throws IOException {
        Save save = new Save(model);
        FileOutputStream fos = new FileOutputStream(saveFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(save);
        oos.flush();
        oos.close();
        fos.close();
    }

    /*
    private void SaveAsBinary(Model model) throws IOException {
        Save save = new Save(model);
        FileOutputStream fos = new FileOutputStream(saveFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (ArtpieceSave artPiece : save.artpieces) {
            oos.writeObject(artPiece);
        }
        for (PersonEntry personEntry : save.adressbook.getPersonList()) {
            oos.writeObject(personEntry);
        }
        oos.flush();
        fos.close();
    }

     */

    public synchronized Model load() throws IOException, ClassNotFoundException, VersionControllException {
        System.out.println("FileHandler : lade");
        Model model = loadAsString();

        return model;
    }

    private Model loadAsString() throws IOException, ClassNotFoundException, VersionControllException {

        FileReader fr = new FileReader(saveFile);
        BufferedReader reader = new BufferedReader(fr);
        Iterator<String> lines = reader.lines().iterator();
        Model model = SaveFileParser.parseFileInput(lines);
        reader.close();
        fr.close();
        reloadAllPictures(model);
        return model;
    }

    public void reloadAllPictures(Model model) {
        for (ArtPieceEntry entry : model.getPieces()) {
            try {
                Image bitmap = loadBitmap(entry.getId());
                entry.setBitmap(bitmap);
            } catch (IOException e) {
                entry.setBitmap(PictureTools.defaultEmptyImage());
                System.out.println("Bild Nr. " + entry.getId() + " konnte nicht geladen werden.");
            }
        }
    }

    /*
        private Model loadBinary() throws IOException, ClassNotFoundException {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            boolean unfinished = true;
            Model model = new Model(new ABModel());
            ArtpieceSave artpieceReference = new ArtpieceSave(ArtPieceEntry.createEmptyArtPieceEntry());
            PersonEntry personReference = new PersonEntry(0, new Person("","","","",null));
            while (unfinished){
                Object o = ois.readObject();
                if (o == null){
                    unfinished = true;
                }else if (o.getClass().isInstance(artpieceReference)){
                    ArtpieceSave artpiece = (ArtpieceSave) o;
                    model.getPieces().add(new ArtPieceEntry(artpiece.getId(), artpiece, null));
                }else if (o.getClass().isInstance(personReference)){
                    PersonEntry person = (PersonEntry) o;
                    model.adressbook.getPersonList().add(person);
                }
            }
            fis.close();
            return model;
        }

         */
    private Model loadBinary() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(saveFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Save save = (Save) ois.readObject();
        ois.close();
        fis.close();
        return createModelFromSave(save);

    }

    private void waitIfSaveFileisNotyetinitialised() {
        while (saveFile == null) {
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Model createModelFromSave(Save save) throws IOException {
        System.out.println("FileHandler : lege neues Model an aus Datei an");
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
        return PictureTools.loadImage(this.bitmapFolder + "/" + id + ".jpg");
    }

    public Image loadHighQualityPicture(int id) throws IOException {
        return PictureTools.loadImage(this.pictureFolder + "/" + id + ".jpg");
    }

}


