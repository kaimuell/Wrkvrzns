package controller;

import adressbook.model.ABModel;
import controller.fileHandler.FileHandler;
import controller.fileHandler.VersionControllException;
import model.elements.ArtPieceEntry;
import model.Model;
import tools.PictureTools;
import view.Viewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Der allgemeine Controller des Werkverzeichnises.
 */

public class ControllerImplementation implements Controller {
    private Model model;
    private final FileHandler fileHandler;
    private List<Viewer> views;
    private List<ArtPieceEntry> selectedElements;
    private final SortAndFilterHandler sortAndFilterHandler;


    public ControllerImplementation(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.model = new Model(new ABModel());
        this.views = new ArrayList<>();
        this.selectedElements = new ArrayList<>();
        this.sortAndFilterHandler = new SortAndFilterHandler(model);
    }


    @Override
    public boolean isASelectedElement(ArtPieceEntry artPiece) {
        if (artPiece == null || selectedElements == null){
            return false;
        }
        return selectedElements.contains(artPiece);
    }

    @Override
    public void selectAdditionalElement(ArtPieceEntry artPieceEntry) {
        if (artPieceEntry != null) {
            selectedElements.add(artPieceEntry);
        }
        informViewsSelectedElementsChanged();
    }

    @Override
    public void selectElement(ArtPieceEntry artPieceEntry) {
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
            SaveCopyOfPicture(imageToLink, entryToChange);
        }
        refreshViews();
    }

    @Override
    public void addEntry(ArtPieceEntry entry, Image imageToLink) {
        if (entry.getId() == -1) { entry.setId(createUnusedID());}

        if (imageToLink != null) {
            entry.setBitmap(imageToLink.getScaledInstance(150,150, Image.SCALE_DEFAULT));
            SaveCopyOfPicture(imageToLink, entry);
        } else {
            entry.setBitmap(PictureTools.defaultEmptyImage());
        }
        model.getPieces().add(entry);
        refreshViews();
    }
    private void SaveCopyOfPicture(Image imageToLink, ArtPieceEntry entry) {
        new Thread( ()-> {
            fileHandler.saveCopyOfPictureLinkedToArtpiece(entry.getId(), imageToLink);
        }).start();
    }

    private void refreshViews() {
        for (Viewer view : views) {view.refreshView(); }
    }

    private int createUnusedID() {
        int id = 0;
        while(model.getEntryWithId(id) != null){
            id++;
        }
        return id;
    }

    @Override
    public void addView(Viewer view) {
            this.views.add(view);
            view.setModelTo(model);
            view.refreshView();
    }

    private void informViewsSelectedElementsChanged(){
        for (Viewer view : views) {
            view.changeBackgroundOfSelectedElements();
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
        }

        //TODO USER INFOMATIONEN GEBEN
    }

    private void updateModelOfViews() {
        for (Viewer view: views) {
            view.setModelTo(this.model);
        }
    }

    @Override
    public void deleteSelectedElements() {
        List<Integer>  deletedIDs = new ArrayList<>();
        for (ArtPieceEntry entry : selectedElements) {
                 model.getPieces().remove(entry);
                 deletedIDs.add(entry.getId());
            }
        fileHandler.deletePicturesAndBitmapsWithIds(deletedIDs);
        refreshViews();
    }

    @Override
    public SortAndFilterHandler SortOrFilter() {
        return this.sortAndFilterHandler;
    }

    @Override
    public void importContacts(File file, boolean onlyContactsWithNames) {
        try{
            fileHandler.importThunderbirdContacts(file, model.adressbook, onlyContactsWithNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createNewProfile(String profileName) {
        try{
            this.model = fileHandler.createNewProfile(profileName);
            updateModelOfViews();
            updateModelOfSubController();
            refreshViews();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAs(String profileName) {
        try {
            fileHandler.createNewProfile(profileName);
            fileHandler.save(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load(File file) {
        try {
            this.model = fileHandler.load(file);
            updateModelOfViews();
            updateModelOfSubController();
            refreshViews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (VersionControllException e) {
            e.printStackTrace();
        }
    }

    private void updateModelOfSubController() {
        sortAndFilterHandler.setModel(model);
    }
}
