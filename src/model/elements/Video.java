package model.elements;

public class Video extends ArtPiece {
    public Video(String name, String technique, int length, int year, boolean isSold, int buyerID) {
        super(name, technique, ArtworkType.VIDEO, 0, 0, 0, length, year, isSold, buyerID);
    }
}
