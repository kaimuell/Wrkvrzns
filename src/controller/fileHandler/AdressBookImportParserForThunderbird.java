package controller.fileHandler;

import adressbook.model.ABModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Die Klasse {@link AdressBookImportParserForThunderbird} parst exportierte Addressbuch Dateien aus Thunderbird. Diese
 * müssen mit UTF8, getrennt durch Komma exportiert sein.
 * Zur Nutzung initialisieren durch den Konstruktor, dann nacheinander load(), parse() und copyToAdressbook() aufrufen.
 */
public class AdressBookImportParserForThunderbird {

    private final File inputFile;
    private final ABModel addressbook;
    private final boolean onlyWithNames;
    private List<ThunderbirdContact> contactBuffer;
    Iterator<String> contacts;


    /**
     * Hilfsklasse zum speichern der Daten
     */
    class ThunderbirdContact{
        String vorname,nachname,anzeigename,spitzname,
                primaereEMailAdresse, sekundäreEMailAdresse,messengerName,telDienstlich,telPrivat,faxNummer,pagerNummer,mobilTel,
                privatAdresse,privatAdresse2,privatOrt,privatBundesland,privatPLZ,privatLand,
                dienstlichAdresse,dienstlichAdresse2,dienstlichOrt,dienstlichBundesland,dienstlichPLZ,dienstlichLand,
                arbeitstitel,abteilung,organisation,webseite1,webseite2,
                geburtsjahr,geburtsmonat,geburtstag,benutzerdef1,benutzerdef2,benutzerdef3,benutzerdef4,notizen;

        public ThunderbirdContact(String vorname, String nachname, String anzeigename, String spitzname,
                                  String primaereEMailAdresse, String sekundäreEMailAdresse, String messengerName, String telDienstlich,
                                  String telPrivat, String faxNummer, String pagerNummer, String mobilTel, String privatAdresse, String privatAdresse2,
                                  String privatOrt, String privatBundesland, String privatPLZ, String privatLand, String dienstlichAdresse,
                                  String dienstlichAdresse2, String dienstlichOrt, String dienstlichBundesland, String dienstlichPLZ, String dienstlichLand,
                                  String arbeitstitel, String abteilung, String organisation, String webseite1, String webseite2, String geburtsjahr,
                                  String geburtsmonat, String geburtstag, String benutzerdef1, String benutzerdef2, String benutzerdef3, String benutzerdef4,
                                  String notizen) {
            this.vorname = vorname;
            this.nachname = nachname;
            this.anzeigename = anzeigename;
            this.spitzname = spitzname;
            this.primaereEMailAdresse = primaereEMailAdresse;
            this.sekundäreEMailAdresse = sekundäreEMailAdresse;
            this.messengerName = messengerName;
            this.telDienstlich = telDienstlich;
            this.telPrivat = telPrivat;
            this.faxNummer = faxNummer;
            this.pagerNummer = pagerNummer;
            this.mobilTel = mobilTel;
            this.privatAdresse = privatAdresse;
            this.privatAdresse2 = privatAdresse2;
            this.privatOrt = privatOrt;
            this.privatBundesland = privatBundesland;
            this.privatPLZ = privatPLZ;
            this.privatLand = privatLand;
            this.dienstlichAdresse = dienstlichAdresse;
            this.dienstlichAdresse2 = dienstlichAdresse2;
            this.dienstlichOrt = dienstlichOrt;
            this.dienstlichBundesland = dienstlichBundesland;
            this.dienstlichPLZ = dienstlichPLZ;
            this.dienstlichLand = dienstlichLand;
            this.arbeitstitel = arbeitstitel;
            this.abteilung = abteilung;
            this.organisation = organisation;
            this.webseite1 = webseite1;
            this.webseite2 = webseite2;
            this.geburtsjahr = geburtsjahr;
            this.geburtsmonat = geburtsmonat;
            this.geburtstag = geburtstag;
            this.benutzerdef1 = benutzerdef1;
            this.benutzerdef2 = benutzerdef2;
            this.benutzerdef3 = benutzerdef3;
            this.benutzerdef4 = benutzerdef4;
            this.notizen = notizen;
        }
    }

    /**
     * Konsturktor
     * @param inputFile Die Datei von der gelesen werden soll
     * @param addressbook Das Adressbuch in das die Kontakte importiert werden sollen
     * @param onlyWithNames Sollen nur Kontakte hinzugefügt werden, bei denen Namen eingegeben sind?
     */
    AdressBookImportParserForThunderbird(File inputFile, ABModel addressbook, boolean onlyWithNames) {
        this.inputFile = inputFile;
        this.addressbook = addressbook;
        this.onlyWithNames = onlyWithNames;
        this.contactBuffer = new ArrayList<>();
    }

    /**
     * Lädt einen Iterator über die Kontakte aus der Datei. Dieser wird in parse verbraucht.
     * @throws IOException Die Datei konnte nicht geladen werden.
     */
    void load() throws IOException {
        //TODO einlesen der Datei
    }

    /**
     * Parsed {@link ThunderbirdContact}s aus dem Iterator der in load erzeugt wurde und speichert diese in einem Buffer.
     * @throws Exception fehler beim parsen.
     */
    void parse() throws Exception{
            while(contacts.hasNext()){
                ThunderbirdContact person = parseSingleContact(contacts.next());
                contactBuffer.add(person);

            }
        }

    private ThunderbirdContact parseSingleContact(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line, ",");
        return new ThunderbirdContact(tokenizer.nextToken(),tokenizer.nextToken(),tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(),
                tokenizer.nextToken(), tokenizer.nextToken());
    }

    /**
     * Kopiert die Kontakte aus dem Buffer in das Adressbuch.
     */
    void copyToAdressbook(){
        //TODO
    }
}

