package controller;

import adressbook.model.ABModel;
import model.elements.ArtPieceEntry;
import view.Views;

import java.awt.*;

public interface Controller {

    /**
     * Gibt zurück ob sich der Eintrag in der Liste der Ausgewählten Einträge befindet
     * @param artPiece der Eintrag
     * @return befindet es sich in der Liste der ausgewählen Einträge?
     */
    boolean isASelectedElement(ArtPieceEntry artPiece);

    /**
     * Fügt der Liste der Ausgewählten Einträge den übergebenen {@link ArtPieceEntry} hinzu
     * @param artPieceEntry der auszuwählende Eintrag
     */
    void selectAdditionalElement(ArtPieceEntry artPieceEntry);

    /**
     * Setzt die Liste der ausgewählten Einträge auf nur den übergebenen {@link ArtPieceEntry}
     * @param artPieceEntry der auszuwählende Eintrag
     */
    void selectElement(ArtPieceEntry artPieceEntry);

    /**
     * Setzt die Felder des {@link ArtPieceEntry} im {@link model.Model} auf
     * die des übergeben und speichert das übergebene Bild unter der Id des Eintrags in die Profildatei.
     * @param entry der Eintrag
     * @param imageToLink das Bild
     */
    void modifyEntry(ArtPieceEntry entry, Image imageToLink);

    /**
     * Fügt den übergebenen Eintrag dem {@link model.Model} hinzu
     * und speichert das übergebene Bild unter der Id des Eintrags in die Profildatei.
     * @param entry der Eitrag
     * @param imageToLink das Bild
     */
    void addEntry(ArtPieceEntry entry, Image imageToLink);

    /**Meldet ein View an den Controller an, setzt das Model des Views auf das Model des Controllers
     *
     * @param view das View
     */
    void addView(Views view);

    /**
     * Gibt das Adressbuch des betrachteten {@link model.Model} zurück
     * @return das Adressbuch
     */
    ABModel getAddressbook();

    /**
     * Speichert das gerade betrachtete {@link model.Model} in dem aktuell geladenen Profil
     */
    void save();

    /**
     * Lädt das im {@link controller.fileHandler.FileHandler} spezifizierte {@link model.Model}
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
