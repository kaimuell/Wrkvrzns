package controller;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * Klasse die die Operationen auf Datein realisiert
 */

public class FileHandler {
    //TODO URL Pfad bestimmen und Datei anlegen.
    private final URL PATH_SETTINGS_FILE  = new URL("");

    public FileHandler() throws MalformedURLException, FileNotFoundException {

            parseInitialSettings(PATH_SETTINGS_FILE);


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
