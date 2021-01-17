package model;

import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;

import java.util.Iterator;

public interface ModelViewAccess {

    int getNumberOfEntries();
    Iterator<ArtPieceEntry> artPiecesToView();

    PersonEntry getPersonWithIDFromAdressBook(int buyerID);
}
