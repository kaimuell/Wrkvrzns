package model.elements;

public class Sculpture extends ArtPiece {
    public Sculpture(String name, String technique, ArtworkType type, int height, int width, int depth, int year, boolean isSold, int buyerID) {
        super(name, technique, ArtworkType.SCULPTURE, height, width, depth, 0, year, isSold, buyerID);
    }
}
