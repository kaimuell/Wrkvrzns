package controller;

import model.Model;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;

/**
 * Die Klasse {@link SortAndFilterHandler} ist eine Unterklasse von {@link ControllerImplementation} und
 * fasst sortier und filter Operationen auf den {@link ArtPieceEntry}s des {@link Model} zusammen.
 */
public class SortAndFilterHandler {
    private Model model;

    SortAndFilterHandler(Model model) {
        this.model = model;
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen,
     * deren Name einen Teil des übergebenen Strings enthalten.
     * @param partOfName der übergebene String
     */
    public void filterByName(String partOfName){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
                model.getPieces()) {
            if (entry.getName().contains(partOfName)){
                model.getFiltertPieces().add(entry);
            }
        }
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen
     * deren Jahr gleich dem übergebenen sind.
     * @param year das jahr
     */
    public void filterByYear(int year){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
             model.getPieces()) {
            if(entry.getYear() == year){
                model.getFiltertPieces().add(entry);
            }
        }
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen,
     * deren Typ gleich dem übergebenen sind
     * @param type der Typ
     */
    public void filterByType (ArtworkType type){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
                model.getPieces()) {
            if (entry.getType() == type){
                model.getFiltertPieces().add(entry);
            }
        }
    }

}
