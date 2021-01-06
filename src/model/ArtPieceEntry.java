package model;

import model.elements.ArtPiece;
import model.elements.ArtworkType;

import java.awt.*;

public class ArtPieceEntry extends ArtPiece {
    private int id;
    private Image bitmap;
    private String picturePath;
    private String bitmapPath;

    public ArtPieceEntry(int id, String name, String technique, ArtworkType type, int height, int width, int depth, int length, int year, int price, boolean isSold, int buyerID, Image bitmap, String picturePath, String bitmapPath) {
        super(name, technique, type, height, width, depth, length, year, price, isSold, buyerID);
        this.id = id;
        this.bitmap = bitmap;
        this.picturePath = picturePath;
        this.bitmapPath = bitmapPath;
    }
    public ArtPieceEntry(int id, ArtPiece p, Image bitmap, String picturePath, String bitmapPath) {
        super(p.getName(), p.getTechnique(), p.getType(), p.getHeight(), p.getWidth(), p.getDepth(), p.getLength(), p.getYear(), p.getPrice(), p.isSold(), p.getBuyerID());
        this.id = id;
        this.bitmap = bitmap;
        this.picturePath = picturePath;
        this.bitmapPath = bitmapPath;
    }

    public void setBitmap(Image bitmap) {
        this.bitmap = bitmap;
    }

    public Image getBitmap() {
        return bitmap;
    }

    public int getId() {
        return id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getBitmapPath() {
        return bitmapPath;
    }

    public void setBitmapPath(String bitmapPath) {
        this.bitmapPath = bitmapPath;
    }

    public void setVariablesTo(ArtPieceEntry otherArtPieceEntry) {
        this.setBitmap(otherArtPieceEntry.getBitmap());
        this.setPicturePath(otherArtPieceEntry.getPicturePath());
        this.setBitmapPath(otherArtPieceEntry.getPicturePath());
        this.setName(otherArtPieceEntry.getName());
        this.setType(otherArtPieceEntry.getType());
        this.setTechnique(otherArtPieceEntry.getTechnique());
        this.setHeight(otherArtPieceEntry.getHeight());
        this.setLength(otherArtPieceEntry.getLength());
        this.setDepth(otherArtPieceEntry.getDepth());
        this.setYear(otherArtPieceEntry.getYear());
        this.setSold(otherArtPieceEntry.isSold());
        this.setBuyerID(otherArtPieceEntry.getBuyerID());
    }


    public static ArtPieceEntry createEmptyArtpieceEntry(){
        return new ArtPieceEntry(-1,
                new ArtPiece("","", ArtworkType.PAINTING,
                        0, 0, 0, 0, 0, 0, false, -1),
                null, "", "");
    }
}