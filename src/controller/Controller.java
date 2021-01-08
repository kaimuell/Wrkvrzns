package controller;

import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;

public interface Controller {
    boolean isASelectedElement(ArtPieceEntry artPiece);
    void addSelectedElement(ArtPieceEntry artPieceEntry);
    void setSelectedElementTo(ArtPieceEntry artPieceEntry);

    void modifyEntry(ArtPieceEntry artPieceEntry);

    void addEntry(ArtPieceEntry artPieceEntry);

    PersonEntry getPersonWithIDFromAddressBook(int buyerID);
}
