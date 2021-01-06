package model;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;

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
    public String getPersonWithIDFromAdressBook(int buyerID) {
        PersonEntry person = adressbuch.getPersonWithID(buyerID);
        return (person.getFirstName() + " " + person.getFamilyName() + ", " + person.geteMail() + ",  " + person.getTel());
    }

    public ArtPieceEntry getEntryWithId(ArtPieceEntry artPieceEntry) {
        for (ArtPieceEntry entry : pieces) {
            if(entry.getId() == artPieceEntry.getId()){
                return entry;
            }
        }
        return null;
    }

}
