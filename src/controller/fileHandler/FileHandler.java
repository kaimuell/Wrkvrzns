package controller.fileHandler;


import adressbook.model.ABModel;
import tools.PictureTools;
import model.Model;
import model.elements.ArtPieceEntry;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


/**
 * Die Klasse {@link FileHandler} realisiert die Verwaltung der gespeicherten Profile und fasst Operationen auf Dateien zusammen.
 */

public class FileHandler {
    private static final String PATH_SETTINGS_FILE  = "./settings.wvs";
    private static final String PROFILE_DIRECTORY = "./profiles/";
    private String pictureFolder;
    private String bitmapFolder;
    private String saveFile;

    /**
     * Konstruktor
     * Lädt bei Initialisierung Informationen aus dem Sekundärspeicher
     */
    public FileHandler() {
        initialiseFileHandler();
        checkIfDefaultProfileExists();
        System.out.println("FileHandler initialisiert");

    }

    private void checkIfDefaultProfileExists() {
        try{
            load();
        } catch (Exception e) {
            createNewProfileFile(PROFILE_DIRECTORY + "default/");
        }
    }

    private void initialiseFileHandler() {
        try {
            loadInitialSettings();
            System.out.println("saveFile = "+ saveFile);
            System.out.println("bitmapFolder = "+ bitmapFolder);
            System.out.println("pictureFolder = "+ pictureFolder);
        } catch (Exception e) {
            System.out.println("FileHandler: erzeuge neue init File");
            createSettingsFileAndEmptySaveFile();
            writeNewPathSettingsToFile();
        }
    }

    private synchronized void loadInitialSettings()throws IOException{
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
            writeNewPathSettingsToFile();
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
    private String pathOfHighQualityPictureWithID(int id){
       return pictureFolder + File.separatorChar + id + ".jpg";
    }

    private String pathOfBitmapWithId (int id){
        return bitmapFolder + File.separatorChar + id + ".jpg";
    }

    private File initialiseFolders() {
        System.out.println("FileHandler : lege Ordner an");
        String folderPath = createDefaultProfileDirectories();
        File saveFile = createNewProfileFile(folderPath);
        return saveFile;
    }

    private String createDefaultProfileDirectories() {
        String rootFolderPath = "./profiles/";
        File profilesFolder = new File(rootFolderPath);
        profilesFolder.mkdir();
        String folderPath = rootFolderPath + "default/";
        File defaultProfileFolder = new File(folderPath);
        defaultProfileFolder.mkdir();
        return folderPath;
    }

    private File createNewProfileFile(String folderPath) {
        File bitmapFolder = new File(folderPath + File.separator +"bitmaps");
        bitmapFolder.mkdir();
        this.bitmapFolder = bitmapFolder.getPath();
        File pictureFolder = new File(folderPath + File.separator + "pictures");
        pictureFolder.mkdir();
        this.pictureFolder = pictureFolder.getPath();
        File saveFile = new File(folderPath + File.separator +"profile.wz");
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
                System.out.println("FileHandler : schreibe Bild nach : " + pathOfHighQualityPictureWithID(artPieceEntryID));
                System.out.println("FileHandler : schreibe Bitmap nach : " + pathOfBitmapWithId(artPieceEntryID));
                String pictureFilename = pathOfHighQualityPictureWithID(artPieceEntryID);
                String bitmapFilename = pathOfBitmapWithId(artPieceEntryID);
                PictureTools.saveImage(picture, new File(pictureFilename), 1.0f);
                PictureTools.saveImage(bitmap, new File(bitmapFilename), 1.0f);
            }catch (IOException e){
                e.printStackTrace();
            }
    }

    //speichert die Pfade des aktuell geladenen Profils in die Datei, aus welcher der Konstruktor sich initialisiert
    private void writeNewPathSettingsToFile()  {
        System.out.println("FileHandler : lege neues PathSettingsFile an");
        try {
            FileOutputStream outputStream = new FileOutputStream(PATH_SETTINGS_FILE);
            BufferedWriter streamWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            streamWriter.write("1.0\n");
            streamWriter.write(saveFile + "\n");
            streamWriter.write(pictureFolder + "\n");
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
        FileInputStream fileInputStream = new FileInputStream(path_settings_file);
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        Iterator lineIterator = reader.lines().iterator();
        if(lineIterator.next().toString().trim().equals("1.0")){
            saveFile = ((String) lineIterator.next()).trim();
            pictureFolder = ((String) lineIterator.next()).trim();
            bitmapFolder = ((String) lineIterator.next()).trim();
        } else {
            System.out.println("Falsche Version");
            throw new VersionControllException();
        }
        reader.close();
        streamReader.close();
        fileInputStream.close();
    }

    /**
     * Speichert das übergebene Model in das geladene Profil
     * @param model das Model
     * @throws IOException
     */
    public synchronized void save(Model model) throws IOException {
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

    /**
     * Lädt das {@link Model} in die geladene Profil Datei
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws VersionControllException
     */
    public synchronized Model load() throws IOException, ClassNotFoundException, VersionControllException {
        System.out.println("FileHandler : lade");
        Model model = loadAsString();

        return model;
    }

    private Model loadAsString() throws IOException, VersionControllException {

        FileReader fr = new FileReader(saveFile);
        BufferedReader reader = new BufferedReader(fr);
        Iterator<String> lines = reader.lines().iterator();
        Model model = SaveFileParser.parseFileInput(lines);
        reader.close();
        fr.close();
        reloadAllBitmaps(model);
        return model;
    }

    /**
     * Lädt alle Bitmaps der Einträge im {@link Model} neu aus dem Sekundärspeicher
     * @param model das {@link Model}
     */
    public void reloadAllBitmaps(Model model) {
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

    /**
     * lädt das Bitmap des Eintrages mit der Id aus dem aktuellen Profil
     * @param id die Id
     * @return das Bitmap
     * @throws IOException keine Datei vorhanden
     */
    public Image loadBitmap(int id) throws IOException {
        return PictureTools.loadImage(pathOfBitmapWithId(id));
    }

    /**
     * lädt das Bild mit hoher Qualität des Eintrags mit der Id aus dem aktuellen Profil
     * @param id die Id
     * @return das Bild
     * @throws IOException keine Datei vorhanden
     */
    public Image loadHighQualityPicture(int id) throws IOException {
        return PictureTools.loadImage(pathOfHighQualityPictureWithID(id));
    }

    /**
     * lösche die Bilder und Bitmaps welche den Ids zugeordnet sind aus dem Profilordner
     * @param deletedIDs die IDs
     */
    public void deletePicturesAndBitmapsWithIds(List<Integer> deletedIDs) {
        for (Integer id : deletedIDs) {
            try {
                Files.delete(Paths.get(pathOfHighQualityPictureWithID(id)));
                Files.delete(Paths.get(pathOfBitmapWithId(id)));
            } catch (IOException e) {
                //Kein Catch da nicht jeder Eintrag zwangsläufig ein Bild gespeichert hat.
            }
        }
    }

    /**
     * Parst die Adressen aus einer .csv Datei in das Adressbuch. Diese müsssen mit Komma getrennt exportiert werden.
     * Idealerweise in UTF-8.
     * @param file Die Datei in die gelesen werden soll
     * @param addressbook Das Adreebuch in die die Kontakte importiert werden sollen.
     * @param onlyWithNames Sollen nur Kontakte importiert werden, für die Namen angegeben sind?
     */
    public void importThunderbirdContacts(File file, ABModel addressbook, boolean onlyWithNames) throws Exception {
        AddressBookImportParserForThunderbird parser = new AddressBookImportParserForThunderbird(file, addressbook, onlyWithNames);
        parser.load();
        parser.parse();
        parser.copyToAdressbook();
    }
}


