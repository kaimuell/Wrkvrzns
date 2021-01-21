package controller.fileHandler;

import adressbook.model.ABModel;
import adressbook.model.Person;
import adressbook.model.PersonEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class AdressBookImportParser {

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

    //TODO parsen neue Thunderbird Kontakte erzeugen umwandeln in Kontakte für das Adressbuch und hinzufügen.
    // 1. Zeile ignorieren, dann teilen durch Komma.
    void parseContactsToAddressBook(String thunderbirdContacts, ABModel, boolean onlyWithNames){
        List<ThunderbirdContact> contactBuffer = new ArrayList<>();
        Iterator<String> contacts = thunderbirdContacts.lines().iterator();
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
}

