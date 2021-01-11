package controller.FileHandler;

import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;

import java.io.Serializable;

class ArtpieceSave extends ArtPiece implements Serializable {
    private int id;


    ArtpieceSave( int id,
            String name, String technique, ArtworkType type, int height, int width, int depth, int length, int year, int price, boolean isSold, int buyerID) {
        super(name, technique, type, height, width, depth, length, year, price, isSold, buyerID);

        this.id = id;
    }

    public ArtpieceSave (ArtPieceEntry entry){
        super((ArtPiece) entry);
        this.id = id;
    }


    public int getId(){
        return id;
    }
}
