package controller.FileHandler;


import controller.PictureController;
import model.Model;
import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * Klasse die die Operationen auf Datein realisiert
 */

public class FileHandler {
    //TODO URL Pfad bestimmen und Datei anlegen.
    private final URL PATH_SETTINGS_FILE  = new URL("");
    private String pictureFolder;
    private String bitmapFolder;
    private String saveFile;


    /**
     * Konstruktor
     * Lädt bei Initialisierung Informationen aus dem Sekundärspeicher
     * @throws MalformedURLException
     */
    public FileHandler() throws MalformedURLException {
            initialise();
    }

    public void relinkPictures(ArtPieceEntry artPieceEntry, String picturePath) throws IOException {
        Image picture = PictureController.loadImage(picturePath);
        Image bitmap = PictureController.createBitmap(picture, 150, 150);
        String pictureFilename = this.pictureFolder + artPieceEntry.getId() + ".jpg";
        String bitmapFilename = this.bitmapFolder + artPieceEntry.getId() + ".png";
        PictureController.saveImage(picture, pictureFilename,  1.0f);
        PictureController.saveImage(bitmap, bitmapFilename, 1.0f);
    }

    public void initialise(){
        try {
            parseInitialSettings((PATH_SETTINGS_FILE));
        } catch (IOException e){
            initialiseNewPathSettingsFile();
        }
    }


    private void initialiseNewPathSettingsFile() {
        //TODO
    }

    private void parseInitialSettings(URL path_settings_file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path_settings_file.getFile());
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        Iterator lineIterator = reader.lines().iterator();

        while(lineIterator.hasNext()){
            //TODO Auslesen der Zeilen und Variablen zuweisen.
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
        Model model = new Model();
        model.adressbook = save.adressbook;
        for (ArtpieceSave artpiece : save.artpieces) {
            model.getPieces().add(
                    new ArtPieceEntry(artpiece.getId(), artpiece, loadBitmap(artpiece.getId())));
        }
        return model;
        }

    private Image loadBitmap(int id) throws IOException {
        return PictureController.loadImage(this.bitmapFolder + id + ".png");
    }
}

}
