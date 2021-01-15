package controller;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import controller.FileHandler.FileHandler;
import controller.FileHandler.VersionControllException;
import model.elements.ArtPieceEntry;
import model.Model;
import tools.PictureTools;
import view.Views;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Der allgemeine Controller des Werkverzeichnises.
 */

public class ControllerImplementation implements Controller {
    private Model model;
    private FileHandler fileHandler;
    private List<Views> views;
    private List<ArtPieceEntry> selectedElements;

    public ControllerImplementation(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.model = new Model(new ABModel());
        this.views = new ArrayList<>();
        this.selectedElements = new ArrayList<>();
    }


    @Override
    public boolean isASelectedElement(ArtPieceEntry artPiece) {
        if (artPiece == null || selectedElements == null){
            return false;
        }
        return selectedElements.contains(artPiece);
    }

    @Override
    public void addSelectedElement(ArtPieceEntry artPieceEntry) {
        if (artPieceEntry != null) {
            selectedElements.add(artPieceEntry);
        }
        informViewsSelectedElementsChanged();
    }

    @Override
    public void setSelectedElementTo(ArtPieceEntry artPieceEntry) {
        this.selectedElements = new ArrayList<>();
        selectedElements.add(artPieceEntry);
        informViewsSelectedElementsChanged();
    }

    @Override
    public void modifyEntry(ArtPieceEntry entry, Image imageToLink) {
        ArtPieceEntry entryToChange = model.getEntryWithId(entry.getId());
        entryToChange.setVariablesTo(entry);
        if (imageToLink != null) {
            entryToChange.setBitmap(imageToLink.getScaledInstance(150,150,Image.SCALE_DEFAULT));
            fileHandler.saveCopyOfPictureLinkedToArtpiece(entryToChange.getId(), imageToLink);
        }
        refreshViews();
        //TODO ungetestet
    }

    private void refreshViews() {
        for (Views view : views) {view.refreshView(); }
    }

    @Override
    public void addEntry(ArtPieceEntry entry, Image imageToLink) {
        if (entry.getId() == -1) { entry.setId(createUnusedID());}

        //TODO TEST LÖSCHEN WENN LANGE GENUG GETESTET
        assert (model.getEntryWithId(entry.getId()) != null);

        if (imageToLink != null) {
            entry.setBitmap(imageToLink.getScaledInstance(150,150, Image.SCALE_DEFAULT));
            System.out.println("Speichere Bildkopien");
            fileHandler.saveCopyOfPictureLinkedToArtpiece(entry.getId(), imageToLink);
        } else {
            entry.setBitmap(PictureTools.defaultEmptyImage());
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
            view.setModelTo(model);
            view.refreshView();
    }

    private void informViewsSelectedElementsChanged(){
        for (Views view : views) {
            view.changeSelectedElements();
        }
    }

    @Override
    public ABModel getAddressbook() {
        return model.adressbook;
    }

    @Override
    public void save() {
        try {
            fileHandler.save(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO USER INFORMATIONEN GEBEN
    }

    @Override
    public void load() {
        try {
            this.model = fileHandler.load();
            updateModelOfViews();
            refreshViews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (VersionControllException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //TODO USER INFOMATIONEN GEBEN
    }

    private void updateModelOfViews() {
        for (Views view: views) {
            view.setModelTo(this.model);
        }
    }

    @Override
    public void deleteSelectedElements() {
        //TODO Dialogfenster das fragt ob selectedElements.size() Elemente gelöscht werden sollen
        List<Integer>  deletedIDs = new ArrayList<>();
        for (ArtPieceEntry entry : selectedElements) {
                 model.getPieces().remove(entry);
                 deletedIDs.add(entry.getId());
            }
        fileHandler.deletePicturesAndBitmapsWithIds(deletedIDs);
        refreshViews();
    }

}
