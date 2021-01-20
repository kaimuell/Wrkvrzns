package model;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Serializable, ModelViewAccess {
    public ABModel adressbook;
    private List<ArtPieceEntry> pieces;

    private List<ArtPieceEntry> filtertPieces;

    public Model(ABModel adressbuch) {
        this.adressbook = adressbuch;
        this.pieces = new ArrayList<>();
        this.filtertPieces = new ArrayList<>();
    }

    public List<ArtPieceEntry> getFiltertPieces() {
        return filtertPieces;
    }

    public List<ArtPieceEntry> getPieces() {
        return pieces;
    }

    @Override
    public int getNumberOfEntries() {
        return pieces.size();
    }

    @Override
    public Iterator<ArtPieceEntry> artPiecesToView() {
        if(filtertPieces.size() == 0) {
            return pieces.iterator();
        }else{
            return filtertPieces.iterator();
        }
    }

    @Override
    public PersonEntry getPersonWithIDFromAdressBook(int buyerID) {
        return  adressbook.getPersonWithID(buyerID);
    }

    public ArtPieceEntry getEntryWithId(int id) {
        for (ArtPieceEntry entry : pieces) {
            if(entry.getId() == id){
                return entry;
            }
        }
        return null;
    }

    public void resetFilteredPieces() {
        this.filtertPieces = new ArrayList<>();
    }
}
