package controller;


import model.elements.ArtPieceEntry;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Klasse die die Operationen auf Datein realisiert
 */

public class FileHandler {
    //TODO URL Pfad bestimmen und Datei anlegen.
    private final URL PATH_SETTINGS_FILE  = new URL("");
    private Path pictureFolder;
    private Path bitmapFolder;
    private Path saveFile;


    /**
     * Konstruktor
     * Lädt bei Initialisierung Informationen aus dem Sekundärspeicher
     * @throws MalformedURLException
     */
    public FileHandler() throws MalformedURLException {
            initialise();
    }

    public static void relinkPictures(ArtPieceEntry artPieceEntry, String picturePath) {
        //TODO
    }

    public void initialise(){
        try {
            parseInitialSettings((PATH_SETTINGS_FILE));
        } catch (FileNotFoundException e) {
            initialiseNewPathSettingsFile();
        }
    }


    private void initialiseNewPathSettingsFile() {
        //TODO
    }

    private void parseInitialSettings(URL path_settings_file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        Iterator lineIterator = reader.lines().iterator();

        while(lineIterator.hasNext()){
            //TODO Auslesen der Zeilen und Variablen zuweisen.
        }
    }
}
