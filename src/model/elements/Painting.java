package model.elements;

public class Painting extends ArtPiece{

    public Painting(String name, String technique,  int height, int width, int year, boolean isSold, int buyerID) {
        super(name, technique, ArtworkType.PAINTING, height, width, 0, 0, year, isSold, buyerID);
    }
}
