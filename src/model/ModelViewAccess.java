package model;

import adressbook.model.PersonEntry;

import java.util.Iterator;

public interface ModelViewAccess {
    int getNumberOfEntries();
    Iterator<ArtPieceEntry> artPieceIterator();

    PersonEntry getPersonWithIDFromAdressBook(int buyerID);
}
