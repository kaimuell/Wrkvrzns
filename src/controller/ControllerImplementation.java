package controller;

import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import controller.FileHandler.FileHandler;
import model.elements.ArtPieceEntry;
import model.Model;
import model.elements.ArtPieceEntryAndPicturePath;
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
    public void modifyEntry(ArtPieceEntryAndPicturePath entryWithPicturePath) {
        ArtPieceEntry entry = entryWithPicturePath.artPieceEntry;
        String picturePath = entryWithPicturePath.picturePath;
        ArtPieceEntry entryToChange = model.getEntryWithId(entry.getId());
        entryToChange.setVariablesTo(entry);
        if (picturePath != null) {
            FileHandler.relinkPictures(entryToChange, picturePath);
        }
        refreshViews();
        //TODO ungetestet
    }

    private void refreshViews() {
        for (Views view : views) {view.refreshView(); }
    }

    @Override
    public void addEntry(ArtPieceEntryAndPicturePath entryWithPicturePath) {
        ArtPieceEntry entry = entryWithPicturePath.artPieceEntry;
        String picturePath = entryWithPicturePath.picturePath;
        if (entry.getId() == -1) { entry.setId(createUnusedID());}

        //TODO TEST LÖSCHEN WENN LANGE GENUG GETESTET
        assert (model.getEntryWithId(entry.getId()) != null);

        if (picturePath != null) {
            FileHandler.relinkPictures(entry, picturePath);
        }
        model.getPieces().add(entry);
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

    @Override
    public ABModel getAddressbook() {
        return this.addressBook;
    }
}
