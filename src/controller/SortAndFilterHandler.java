package controller;

import model.Model;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;

import java.util.Locale;

/**
 * Die Klasse {@link SortAndFilterHandler} ist eine Unterklasse von {@link ControllerImplementation} und
 * fasst sortier und filter Operationen auf den {@link ArtPieceEntry}s des {@link Model} zusammen.
 */
public class SortAndFilterHandler {
    private final ControllerImplementation controller;
    private Model model;

    SortAndFilterHandler(Model model, ControllerImplementation controller) {
        this.model = model;
        this.controller = controller;
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen,
     * deren Name einen Teil des übergebenen Strings enthalten.
     * @param partOfName der übergebene String
     */
    public void filterByName(String partOfName){
        System.out.println(partOfName);
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
                model.getPieces()) {
            System.out.println(entry.getName());
            if (entry.getName().toLowerCase().contains(partOfName.toLowerCase())){
                System.out.println("Eintag gefunden : " + entry.getName());
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen
     * deren Jahr teilweise dem übergebenen entsprechen.
     * @param year das jahr
     */
    public void filterByYear(String year){
        model.resetFilteredPieces();
        System.out.println("Suche Jahr : " + year);
        for (ArtPieceEntry entry:
             model.getPieces()) {
            if(String.valueOf(entry.getYear()).contains(year)){
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen,
     * deren Typ gleich dem übergebenen sind
     * @param type der Typ
     */
    public void filterByType (String type){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
                model.getPieces()) {
            if (entry.getType().toString().contains(type.toUpperCase())){
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }

    public void filterByTechnique(String technique){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry: model.getPieces()) {
            if(entry.getTechnique().toLowerCase().contains(technique.toLowerCase())){
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }



    protected void setModel(Model model){
        this.model = model;
    }

}
