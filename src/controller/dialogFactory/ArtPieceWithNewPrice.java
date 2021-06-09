package controller.dialogFactory;

import model.elements.ArtPiece;

public class ArtPieceWithNewPrice {
    private ArtPiece artPiece;
    private int newPrice;

    public ArtPieceWithNewPrice(ArtPiece artPiece, int newPrice) {
        this.artPiece = artPiece;
        this.newPrice = newPrice;
    }

    public ArtPiece getArtPiece() {
        return artPiece;
    }

    public void setArtPiece(ArtPiece artPiece) {
        this.artPiece = artPiece;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }
}
