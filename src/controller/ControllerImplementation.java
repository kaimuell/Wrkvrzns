package controller;

import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import model.elements.ArtPieceEntry;
import model.Model;
import view.Views;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Der allgemeine Controller des Werkverzeichnises.
 */

public class ControllerImplementation implements Controller {
    private ABController addressbookController;
    private ABModel addressBook;
    private Model model;
    private FileHandler fileHandler;
    private List<Views> views;
    private List<ArtPieceEntry> selectedElements;

    public ControllerImplementation(Model model, ABModel addressBook) {
        this.model = model;
        this.views = new ArrayList<>();
        try {
            this.fileHandler = new FileHandler();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        refreshViews();
    }

    @Override
    public void setSelectedElementTo(ArtPieceEntry artPieceEntry) {
        this.selectedElements = new ArrayList<>();
        selectedElements.add(artPieceEntry);
        refreshViews();
    }

    @Override
    public void modifyEntry(ArtPieceEntry artPieceEntry) {
        ArtPieceEntry entryToChange = model.getEntryWithId(artPieceEntry.getId());
        entryToChange.setVariablesTo(artPieceEntry);
        refreshViews();
        //TODO ungetestet
    }

    private void refreshViews() {
        for (Views view : views) {view.refreshView(); }
    }

    @Override
    public void addEntry(ArtPieceEntry artPieceEntry) {
        if (artPieceEntry.getId() == -1) { artPieceEntry.setId(createUnusedID());}
        //TODO TEST LÖSCHEN WENN LANGE GENUG GETESTET
        assert (model.getEntryWithId(artPieceEntry.getId()) != null);
        FileHandler.relinkPictures(artPieceEntry);
        model.getPieces().add(artPieceEntry);
        refreshViews();
    }


    private int createUnusedID() {
        int id = 0;
        while(model.getEntryWithId(id) != null){
            id++;
        }
        return id;
    }

    @Override
    public PersonEntry getPersonWithIDFromAddressBook(int buyerID) {
        return model.getPersonWithIDFromAdressBook(buyerID);
    }

    @Override
    public void addView(Views view) {
            this.views.add(view);
    }
}
