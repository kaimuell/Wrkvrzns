package controller;

import adressbook.model.ABModel;
import controller.fileHandler.FileHandler;
import controller.fileHandler.VersionControlException;
import exhibitions.entities.Exhibition;
import exhibitions.model.ExhibitionsModel;
import gui.MessageBord;
import model.elements.ArtPieceEntry;
import model.Model;
import tools.PictureTools;
import view.Viewer;

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
    private Model model;
    private final FileHandler fileHandler;
    private List<Viewer> views;
    private List<ArtPieceEntry> selectedElements;
    private final SortAndFilterHandler sortAndFilterHandler;
    private List<MessageBord> messageBords;


    public ControllerImplementation(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.model = new Model(new ABModel(), new ExhibitionsModel(null));
        this.views = new ArrayList<>();
        this.messageBords = new ArrayList<>();
        this.selectedElements = new ArrayList<>();
        this.sortAndFilterHandler = new SortAndFilterHandler(model, this);
    }


    @Override
    public void addView(Viewer view) {
            this.views.add(view);
            view.setModelTo(model);
            view.refreshView();
    }

    @Override
    public void addMessageBord(MessageBord messageBord) {
        this.messageBords.add(messageBord);
    }

    @Override
    public Model getModel() {
        return this.model;
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
        model.getPieces().add(entry);
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
        while(model.getEntryWithId(id) != null){
            id++;
        }
        return id;
    }

    @Override
    public ABModel getAddressbook() {
        return model.getAdressbook();
    }

    @Override
    public void save() {
        try {
            fileHandler.save(model);
            pushMessageToMessageBords("Werkverzeichnis gespeichert");
        } catch (IOException e) {
            pushMessageToMessageBords("Speichern fehlgeschlagen");
        }
    }

    @Override
    public void saveAs(String profileName) {
        try {
            fileHandler.createNewProfile(profileName);
            fileHandler.save(model);
            pushMessageToMessageBords("Gespeichert als " + profileName);
        } catch (IOException e) {
            pushMessageToMessageBords("Speichern fehlgeschlagen");
        }
    }

    @Override
    public void load() {
        try {
            this.model = fileHandler.load();
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
            this.model = fileHandler.load(file);
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
        for (Iterator<Exhibition> it = model.exhibitions.getExhibitonIterator(); it.hasNext(); ) {
            Exhibition e = it.next();
            if (e.getId() == idOfLastEntry){
                return e;
            }
        }
        return null;
    }


    @Override
    public void deleteSelectedElements() {
        List<Integer>  deletedIDs = new ArrayList<>();
        int counter = 0;
        for (ArtPieceEntry entry : selectedElements) {
                 model.getPieces().remove(entry);
                 model.getFiltertPieces().remove(entry);
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
            fileHandler.importThunderbirdContacts(file, model.getAdressbook(), onlyContactsWithNames);
        } catch (Exception e) {
            pushMessageToMessageBords("Import fehlgeschlagen");
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
            pushMessageToMessageBords("Neues Profil konnte nicht erzeugt werden");
        }
    }

    private void updateModelOfViews() {
        for (Viewer view: views) {
            view.setModelTo(this.model);
        }
    }
    private void updateModelOfSubController() {
        sortAndFilterHandler.setModel(model);
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
