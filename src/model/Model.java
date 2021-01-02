package model;

import adressbook.model.ABModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model implements Serializable, ModelViewAccess {
    ABModel adressbuch;
    List<ArtPieceEntry> pieces;

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
}
