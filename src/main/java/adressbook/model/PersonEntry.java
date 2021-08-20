package adressbook.model;

import java.io.Serializable;

/**
 * Der Eintrag einer Person im Adressbuch, enthält zusätzlich eine eindeutige ID, sowie eine Zahl die Angibt wieviele externe Links existieren,
 */

public class PersonEntry extends Person implements Serializable {
    int id;
    int links;


    public PersonEntry(int id, Person p) {
        super(p.getFamilyName(), p.getFirstName(), p.geteMail(), p.getTel(), p.getAdress(), p.getType());
        this.id = id;
        this.links = 0;
    }

    public int getId() {
        return id;
    }

    public int getLinks() {
        return links;
    }

    public void setLinks(int links) {
        this.links = links;
    }
}
