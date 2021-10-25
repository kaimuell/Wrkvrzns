package model.elements;

import adressbook.model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtPiece implements Serializable {
    private String name;
    private String technique;
    private ArtworkType type;
    private int height;
    private int width;
    private int depth;
    private int length;
    private int year;
    private int edition;
    private List<Person> buyers;
    private int price;
    private String strorageLocation;

    public ArtPiece(String name, String technique, ArtworkType type, int height, int width, int depth, int length, int year, int price, int edition, String storageLocation) {
        this.name = name;
        this.technique = technique;
        this.type = type;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.length = length;
        this.year = year;
        this.price = price;
        this.edition = edition;
        this.strorageLocation = storageLocation;
        this.buyers = new ArrayList<>();
    }

    public ArtPiece(ArtPiece piece) {
        this.name = piece.getName();
        this.technique = piece.getTechnique();
        this.type = piece.getType();
        this.height = piece.getHeight();
        this.width = piece.getWidth();
        this.depth = piece.getDepth();
        this.year = piece.getYear();
        this.price = piece.getPrice();
        this.edition = piece.getEdition();
        this.buyers = piece.getBuyers();
    }

    /**
     * Ereugt eine Reprässentation der Maße, welche dem Typ des Kunstwerks entspricht.
     *
     * @return die Reprässentation
     */
    public String getSizeRepresentation() {
        if (type == ArtworkType.PAINTING || type == ArtworkType.GRAFIK) {
            return (height + " x " + width + " cm");
        } else if (type == ArtworkType.SCULPTURE || type == ArtworkType.INSTALLATION) {
            return (height + " x " + width + " x " + depth + " cm");
        } else if (type == ArtworkType.VIDEO) {
            return (length + " min");
        }
        return ("");
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public ArtworkType getType() {
        return type;
    }

    public void setType(ArtworkType type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Person> getBuyers() {
        return buyers;
    }

    public void addBuyer(Person buyer) {
        this.buyers.add(buyer);
    }

    /**
     * Gibt eine Repräsentation des Käufers aus Name, NAchname und email zurück
     *
     * @return die Repräsentation
     */
    public String getBuyersRepresentation() {
        if (buyers.isEmpty()) {
            return "nicht verkauft";
        } else if (buyers.size() == 1) {
            Person uniqueBuyer = buyers.get(0);
            return uniqueBuyer.getFirstName() + " " + uniqueBuyer.getFamilyName();
        } else {
            return buyers.size() + " Käufer";
        }
    }

    /**
     * Gibt eine Repräsentetion zurück welche darstellt wie viele Stücke der Edition noch vorhanden sind
     *
     * @return die Repräsentation
     */
    public String getEditionRepresentation() {
        return (this.edition - buyers.size()) + " / " + this.edition;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBuyers(List<Person> buyers) {
        this.buyers = buyers;
    }

    public String getStorageLocation() {
        return strorageLocation;
    }

    public void setStorageLocation(String strorageLocation) {
        this.strorageLocation = strorageLocation;
    }
}