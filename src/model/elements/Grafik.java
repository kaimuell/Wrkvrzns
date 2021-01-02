package model.elements;

public class Grafik extends ArtPiece{
    public Grafik(String name, String technique, int height, int width,  int year, boolean isSold, int buyerID) {
        super(name, technique, ArtworkType.GRAFIK, height, width, 0, 0, year, isSold, buyerID);
    }
}
