package controller;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;
import view.Views;

import java.awt.*;

public interface Controller {
    boolean isASelectedElement(ArtPieceEntry artPiece);
    void addSelectedElement(ArtPieceEntry artPieceEntry);
    void setSelectedElementTo(ArtPieceEntry artPieceEntry);

    void modifyEntry(ArtPieceEntry entry, Image imageToLink);

    void addEntry(ArtPieceEntry entry, Image imageToLink);

    PersonEntry getPersonWithIDFromAddressBook(int buyerID);

    /**Meldet ein View an den Controller an, setzt das Model des Views auf das Model des Controllers
     *
     * @param view das View
     */
    void addView(Views view);

    ABModel getAddressbook();

    void save();

    void load();
}
