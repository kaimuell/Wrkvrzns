package adressbook.model;

public class PersonEntry extends Person {
    int id;
    int links;


    public PersonEntry(int id, Person p) {
        super(p.getFamilyName(), p.getFirstName(), p.geteMail(), p.getTel(), p.getAdress());
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
