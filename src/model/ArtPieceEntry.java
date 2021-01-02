package model;

import model.elements.ArtPiece;
import model.elements.ArtworkType;

import java.awt.*;

public class ArtPieceEntry extends ArtPiece {
    Image bitmap;
    String picturePath;
    String bitmapPath;

    public ArtPieceEntry(String name, String technique, ArtworkType type, int height, int width, int depth, int length, int year, boolean isSold, int buyerID, Image bitmap, String picturePath, String bitmapPath) {
        super(name, technique, type, height, width, depth, length, year, isSold, buyerID);
        this.bitmap = bitmap;
        this.picturePath = picturePath;
        this.bitmapPath = bitmapPath;
    }
    public ArtPieceEntry(ArtPiece p, Image bitmap, String picturePath, String bitmapPath) {
        super(p.getName(), p.getTechnique(), p.getType(), p.getHeight(), p.getWidth(), p.getDepth(), p.getLength(), p.getYear(), p.isSold(), p.getBuyerID());
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
}