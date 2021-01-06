package controller;

import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;
import model.ArtPieceEntry;
import model.Model;
import view.Views;

import java.util.ArrayList;
import java.util.List;


/**
 * Der allgemeine Controller des Werkverzeichnises.
 */

public class ControllerImplementation implements Controller {
    private ABController addressbookController;
    private ABModel addressBook;
    private Model model;
    private List<Views> views;
    private List<ArtPieceEntry> selectedElements;

    public ControllerImplementation(Model model, ABModel addressBook) {
        this.model = model;
        this.views = new ArrayList<>();
        this.addressBook = addressBook;
        this.addressbookController = new ABControllerImplementation(addressBook);
        this.selectedElements = new ArrayList<>();
    }


    @Override
    public boolean isASelectedElement(ArtPieceEntry artPiece) {
        return selectedElements.contains(artPiece);
    }

    @Override
    public void addSelectedElement(ArtPieceEntry artPieceEntry) {
        selectedElements.add(artPieceEntry);
    }

    @Override
    public void setSelectedElementTo(ArtPieceEntry artPieceEntry) {
        this.selectedElements = new ArrayList<>();
        selectedElements.add(artPieceEntry);
    }

    @Override
    public void modifyEntry(ArtPieceEntry artPieceEntry) {
        ArtPieceEntry entryToChange = model.getEntryWithId(artPieceEntry);
        entryToChange.setVariablesTo(artPieceEntry);
        //TODO ungetestet
    }

    @Override
    public void addEntry(ArtPieceEntry artPieceEntry) {
        model.getPieces().add(artPieceEntry);
    }

    @Override
    public String getPersonWithIDFromAdressBook(int buyerID) {
        return model.getPersonWithIDFromAdressBook(buyerID);
    }
}
