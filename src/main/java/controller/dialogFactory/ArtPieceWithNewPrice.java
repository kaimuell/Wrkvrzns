package controller.dialogFactory;

import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;

public class ArtPieceWithNewPrice {
    private ArtPieceEntry artPiece;
    private int newPrice;

    public ArtPieceWithNewPrice(ArtPieceEntry artPiece, int newPrice) {
        this.artPiece = artPiece;
        this.newPrice = newPrice;
    }

    public ArtPieceEntry getArtPiece() {
        return artPiece;
    }

    public void setArtPiece(ArtPieceEntry artPiece) {
        this.artPiece = artPiece;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }
}
