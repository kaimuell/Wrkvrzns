package model;

import java.util.Iterator;

public interface ModelViewAccess {
    int getNumberOfEntries();
    Iterator<ArtPieceEntry> artPieceIterator();
}
