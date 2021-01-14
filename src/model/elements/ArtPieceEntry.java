package model.elements;

import adressbook.model.Person;

import java.awt.*;
import java.io.Serializable;

public class ArtPieceEntry extends ArtPiece implements Serializable {
    private int id;
    private Image bitmap;


    public ArtPieceEntry(int id, String name, String technique, ArtworkType type, int height, int width, int depth,
                         int length, int year, int price, Person buyer, Image bitmap) {
        super(name, technique, type, height, width, depth, length, year, price, buyer);
        this.id = id;
        this.bitmap = bitmap;

    }
    public ArtPieceEntry(int id, ArtPiece p, Image bitmap) {
        super(p.getName(), p.getTechnique(), p.getType(), p.getHeight(), p.getWidth(), p.getDepth(), p.getLength(), p.getYear(), p.getPrice(), p.getBuyer());
        this.id = id;
        this.bitmap = bitmap;

    }



    public void setBitmap(Image bitmap) {
        this.bitmap = bitmap;
    }

    public Image getBitmap() {
        return bitmap;
    }


    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gibt die eindeutige Id des Eintrags zurück.
     * Gibt es noch keinen Eintrag in der Liste des Modells ist diese auf -1 gesetzt
     * @return die Id
     */
    public int getId() {
        return id;
    }



    public void setVariablesTo(ArtPieceEntry otherArtPieceEntry) {
        this.setBitmap(otherArtPieceEntry.getBitmap());
        this.setName(otherArtPieceEntry.getName());
        this.setType(otherArtPieceEntry.getType());
        this.setTechnique(otherArtPieceEntry.getTechnique());
        this.setHeight(otherArtPieceEntry.getHeight());
        this.setLength(otherArtPieceEntry.getLength());
        this.setDepth(otherArtPieceEntry.getDepth());
        this.setYear(otherArtPieceEntry.getYear());
        this.setBuyer(otherArtPieceEntry.getBuyer());
    }


    public static ArtPieceEntry createEmptyArtPieceEntry(){
        return new ArtPieceEntry(-1,
                new ArtPiece("","", ArtworkType.PAINTING,
                        0, 0, 0, 0, 0, 0, null),
                null);
    }

}