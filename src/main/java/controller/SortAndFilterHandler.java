package controller;

import gui.elements.SortingType;
import model.Model;
import model.elements.ArtPieceEntry;


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
        model.resetFilteredPieces();
        for (ArtPieceEntry entry:
                model.getPieces()) {
            if (entry.getName().toLowerCase().contains(partOfName.toLowerCase())){
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }

    /**
     * Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen
     * deren Jahr teilweise dem übergebenen String entsprechen.
     * @param year der String
     */
    public void filterByYear(String year){
        model.resetFilteredPieces();
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
     * deren Typ Teilweise dem übergebenen String entsprechen
     * @param type der String
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

    /**
     *  Filtert die Einträge im {@link Model} und setzt die Liste der gefilterten Einträge auf diejenigen,
     *  deren Technik einen Teil des übergebenen Strings enthalten.
     *
     * @param technique der String
     */
    public void filterByTechnique(String technique){
        model.resetFilteredPieces();
        for (ArtPieceEntry entry: model.getPieces()) {
            if(entry.getTechnique().toLowerCase().contains(technique.toLowerCase())){
                model.getFiltertPieces().add(entry);
            }
        }
        controller.refreshViews();
    }

    /**
     * Sortiert die Listen im {@link Model} nach dem übergebenen SortierTyp
     * @param sortingType der SortierTyp
     */
    public void SortBy (SortingType sortingType) {
        if (sortingType == SortingType.NAME_SMALLER) {
            model.getFiltertPieces().sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
            model.getPieces().sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        } else if (sortingType == SortingType.NAME_BIGGER) {
            model.getFiltertPieces().sort((o1, o2) -> -(o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase())));
            model.getPieces().sort((o1, o2) -> -(o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase())));
        } else if (sortingType == SortingType.TYPE_SMALLER) {
            model.getFiltertPieces().sort((o1, o2) -> o1.getType().compareTo(o2.getType()));
            model.getPieces().sort((o1, o2) -> o1.getType().compareTo(o2.getType()));
        } else if (sortingType == SortingType.TYPE_BIGGER) {
            model.getFiltertPieces().sort((o1, o2) -> -o1.getType().compareTo(o2.getType()));
            model.getPieces().sort((o1, o2) -> -o1.getType().compareTo(o2.getType()));
        } else if (sortingType == SortingType.YEAR_SMALLER) {
            model.getFiltertPieces().sort((o1, o2) -> o1.getYear() - o2.getYear());
            model.getPieces().sort((o1, o2) -> o1.getYear() - o2.getYear());
        } else if (sortingType == SortingType.YEAR_BIGGER) {
            model.getFiltertPieces().sort((o1, o2) -> o2.getYear() - o1.getYear());
            model.getPieces().sort((o1, o2) -> o2.getYear() - o1.getYear());
        } else if (sortingType == SortingType.PRICE_SMALLER) {
            model.getFiltertPieces().sort((o1, o2) -> o1.getPrice() - o2.getPrice());
            model.getPieces().sort((o1, o2) -> o1.getPrice() - o2.getPrice());
        } else if (sortingType == SortingType.PRICE_BIGGER) {
            model.getFiltertPieces().sort((o1, o2) -> o2.getPrice() - o1.getPrice());
            model.getPieces().sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        }
        controller.refreshViews();
    }


    protected void setModel(Model model){
        this.model = model;
    }
}
