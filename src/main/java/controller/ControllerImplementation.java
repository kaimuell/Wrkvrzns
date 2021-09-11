package controller;

import adressbook.model.ABModel;
import controller.fileHandler.FileHandler;
import controller.fileHandler.VersionControlException;
import exhibitions.entities.Exhibition;
import exhibitions.model.ExhibitionsModel;
import gui.MessageBord;
import model.ModelContainer;
import model.elements.ArtPieceEntry;
import model.Model;
import tools.PictureTools;
import gui.view.Viewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Der allgemeine Controller des Werkverzeichnises.
 */

public class ControllerImplementation implements Controller {
    private final FileHandler fileHandler;
    private List<Viewer> views;
    private List<ArtPieceEntry> selectedElements;
    private final SortAndFilterHandler sortAndFilterHandler;
    private List<MessageBord> messageBords;


    public ControllerImplementation(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.views = new ArrayList<>();
        this.messageBords = new ArrayList<>();
        this.selectedElements = new ArrayList<>();
        this.sortAndFilterHandler = new SortAndFilterHandler(ModelContainer.getModel(), this);
    }


    @Override
    public void addView(Viewer view) {
            this.views.add(view);
            view.setModelTo(ModelContainer.getModel());
            view.refreshView();
    }

    @Override
    public void addMessageBord(MessageBord messageBord) {
        this.messageBords.add(messageBord);
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
    public List<ArtPieceEntry> getSelectedElements() {
        return selectedElements;
    }

    @Override
    public void modifyEntry(ArtPieceEntry entry, Image imageToLink) {
        ArtPieceEntry entryToChange = ModelContainer.getModel().getEntryWithId(entry.getId());
        entryToChange.setVariablesTo(entry);
        if (imageToLink != null) {
            entryToChange.setBitmap(PictureTools.createBitmap(imageToLink, 250, 250));
            SaveCopyOfPicture(imageToLink, entryToChange);
        }
        pushMessageToMessageBords("Eintrag modifiziert");
        refreshViews();
    }

    @Override
    public void addEntry(ArtPieceEntry entry, Image imageToLink) {
        if (entry.getId() == -1) { entry.setId(createUnusedID());}

        if (imageToLink != null) {
            entry.setBitmap(PictureTools.createBitmap(imageToLink, 250, 250));
            SaveCopyOfPicture(imageToLink, entry);
        } else {
            entry.setBitmap(PictureTools.defaultEmptyImage());
        }
        ModelContainer.getModel().getPieces().add(entry);
        pushMessageToMessageBords("Eintrag angelegt");
        refreshViews();
    }

    private void SaveCopyOfPicture(Image imageToLink, ArtPieceEntry entry) {
        new Thread( ()-> {
            fileHandler.saveCopyOfPictureLinkedToArtpiece(entry.getId(), imageToLink);
        }).start();
    }

    private int createUnusedID() {
        int id = 0;
        while(ModelContainer.getModel().getEntryWithId(id) != null){
            id++;
        }
        return id;
    }

    @Override
    public void save() {
        try {
            fileHandler.save(ModelContainer.getModel());
            pushMessageToMessageBords("Werkverzeichnis gespeichert");
        } catch (IOException e) {
            pushMessageToMessageBords("Speichern fehlgeschlagen");
        }
    }

    @Override
    public void saveAs(String profileName) {
        try {
            fileHandler.createNewProfile(profileName);
            fileHandler.save(ModelContainer.getModel());
            pushMessageToMessageBords("Gespeichert als " + profileName);
        } catch (IOException e) {
            pushMessageToMessageBords("Speichern fehlgeschlagen");
        }
    }

    @Override
    public void load() {
        try {
            ModelContainer.setModel(fileHandler.load());
            updateModelOfSubController();
            updateModelOfViews();
            pushMessageToMessageBords("Werkverzeichnis geladen");
            refreshViews();
        } catch (IOException e) {
            pushMessageToMessageBords("Laden fehlgeschlagen : Konnte Datei nicht öffnen");
        } catch (VersionControlException e) {
            pushMessageToMessageBords("Laden fehlgeschlagen : Dateiversion unbekannt");
        }
    }

    @Override
    public void load(File file) {
        try {
            ModelContainer.setModel(fileHandler.load(file));
            updateModelOfViews();
            updateModelOfSubController();
            pushMessageToMessageBords("Werkverzeichnis geladen");
            refreshViews();
        } catch (IOException e) {
            pushMessageToMessageBords("Laden fehlgeschlagen : Konnte Datei nicht öffnen.");
        } catch (VersionControlException e) {
            pushMessageToMessageBords("Laden fehlgeschlagen : Datei Version unbekannt.");
        }
    }

    @Override
    public Exhibition getExhibitionWithID(int idOfLastEntry) {
        for (Iterator<Exhibition> it = ModelContainer.getModel().getExhibitions().getExhibitonIterator(); it.hasNext(); ) {
            Exhibition e = it.next();
            if (e.getId() == idOfLastEntry){
                return e;
            }
        }
        return null;
    }

    @Override
    public Image loadPictureOf(ArtPieceEntry entry) {
        try {
            return fileHandler.loadHighQualityPicture(entry.getId());
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    public void deleteSelectedElements() {
        List<Integer>  deletedIDs = new ArrayList<>();
        int counter = 0;
        for (ArtPieceEntry entry : selectedElements) {
                 ModelContainer.getModel().getPieces().remove(entry);
                 ModelContainer.getModel().getFiltertPieces().remove(entry);
                 deletedIDs.add(entry.getId());
                 counter++;
            }
        fileHandler.deletePicturesAndBitmapsWithIds(deletedIDs);
        pushMessageToMessageBords(counter + "Einträge gelöscht");
        refreshViews();
    }

    @Override
    public SortAndFilterHandler SortOrFilter() {
        return this.sortAndFilterHandler;
    }

    @Override
    public void importContacts(File file, boolean onlyContactsWithNames) {
        try{
            fileHandler.importThunderbirdContacts(file, ModelContainer.getModel().getAdressbook(), onlyContactsWithNames);
        } catch (Exception e) {
            pushMessageToMessageBords("Import fehlgeschlagen");
        }
    }

    @Override
    public void createNewProfile(String profileName) {
        try{
            ModelContainer.setModel(fileHandler.createNewProfile(profileName));
            updateModelOfViews();
            updateModelOfSubController();
            refreshViews();
        } catch (IOException e) {
            pushMessageToMessageBords("Neues Profil konnte nicht erzeugt werden");
        }
    }

    private void updateModelOfViews() {
        for (Viewer view: views) {
            view.setModelTo(ModelContainer.getModel());
        }
    }
    private void updateModelOfSubController() {
        sortAndFilterHandler.setModel(ModelContainer.getModel());
    }

    protected void refreshViews() {
        for (Viewer view : views) {view.refreshView(); }
    }

    private void informViewsSelectedElementsChanged(){
        for (Viewer view : views) {
            view.changeBackgroundOfSelectedElements();
        }
    }

    private void pushMessageToMessageBords(String message){
        for (MessageBord messageBord: messageBords) {
            messageBord.pushMessage(message);
        }
    }


}
