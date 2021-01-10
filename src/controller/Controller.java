package controller;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;
import model.elements.ArtPieceEntryAndPicturePath;
import view.Views;

public interface Controller {
    boolean isASelectedElement(ArtPieceEntry artPiece);
    void addSelectedElement(ArtPieceEntry artPieceEntry);
    void setSelectedElementTo(ArtPieceEntry artPieceEntry);

    void modifyEntry(ArtPieceEntryAndPicturePath entryWithPicturePath);

    void addEntry(ArtPieceEntryAndPicturePath entryWithPicturePath);

    PersonEntry getPersonWithIDFromAddressBook(int buyerID);

    void addView(Views view);

    ABModel getAddressbook();
}
