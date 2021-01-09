package model;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Serializable, ModelViewAccess {
    private ABModel adressbuch;
    private List<ArtPieceEntry> pieces;

    public Model(ABModel adressbuch) {
        this.adressbuch = adressbuch;
        this.pieces = new ArrayList<>();
    }

    public List<ArtPieceEntry> getPieces() {
        return pieces;
    }

    @Override
    public int getNumberOfEntries() {
        return pieces.size();
    }

    @Override
    public Iterator<ArtPieceEntry> artPieceIterator() {
        return pieces.iterator();
    }

    @Override
    public PersonEntry getPersonWithIDFromAdressBook(int buyerID) {
        return  adressbuch.getPersonWithID(buyerID);
    }

    public ArtPieceEntry getEntryWithId(int id) {
        for (ArtPieceEntry entry : pieces) {
            if(entry.getId() == id.getId()){
                return entry;
            }
        }
        return null;
    }

}
