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

    /**Meldet ein View an den Controller an, setzt das Model des Views auf das Model des Controllers
     *
     * @param view das View
     */
    void addView(Views view);

    ABModel getAddressbook();
}
