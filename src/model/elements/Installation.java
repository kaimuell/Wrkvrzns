package model.elements;

public class Installation extends ArtPiece {
    public Installation(String name, String technique, int height, int width, int depth, int year, boolean isSold, int buyerID) {
        super(name, technique, ArtworkType.INSTALLATION, height, width, depth, 0, year, isSold, buyerID);
    }
}
