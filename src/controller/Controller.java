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

    /**
     * Speichert das gerade betrachtete {@link model.Model} in dem aktuell geladenen Profil
     */
    void save();

    /**
     * Lädt das im {@link controller.FileHandler.FileHandler} spezifizierte {@link model.Model}
     */
    void load();

    /**
     * löscht die ausgewählten Elemente aus dem {@link model.Model}
     */
    void deleteSelectedElements();

    /**
     * Getter für die Sortier und Filter Operationen durch den {@link SortAndFilterHandler}
     * @return die Sortier und Filter Operationen
     */
    SortAndFilterHandler SortOrFilter();
}
