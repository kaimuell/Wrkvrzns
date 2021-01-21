package model;

import model.elements.ArtPieceEntry;

import java.util.Iterator;

public interface ModelViewAccess {

    int getNumberOfEntries();
    Iterator<ArtPieceEntry> artPiecesToView();
}
