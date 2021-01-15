package controller.DialogController;

import adressbook.model.PersonEntry;
import controller.Controller;
import model.elements.ArtPieceEntry;

import javax.swing.*;

import java.io.File;

import static controller.DialogController.OkCancelOption.*;
import static java.lang.Thread.sleep;

/**
 * Realisiert Dialog Aufrufe
 */

public class DialogController {
    private Controller controller;

    public DialogController(Controller controller) {

        this.controller = controller;

    }

    public Thread createModifyEntryDialogThread(ArtPieceEntry artPieceEntry) {
        return new Thread( () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(artPieceEntry, controller);
            while (dialog.okCancelOption() == UNDECIDED) {
                waitFor50Milis();
            }
            if (dialog.okCancelOption() == OK) { controller.modifyEntry(dialog.getEntryInfo(), dialog.getUpdatedImage()); }
            dialog.dispose();
        });
    }

    public Thread createNewArtPieceDialogThread(){
        return new Thread( () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(controller);
            while (dialog.okCancelOption() == UNDECIDED) {
                waitFor50Milis();
            }
            if (dialog.okCancelOption() == OK) { controller.addEntry(dialog.getEntryInfo(), dialog.getUpdatedImage()); }
            dialog.dispose();
        });
    }

    /**
     * Erstellt einen Frame aus dem Dressbuch indem eine Person ausgewählt werden kann
     * und gibt die ausgewählte Person zurück
     * @return die Person / null falls keine gewählt
     */
    public PersonEntry selectPersonFromAddressBookDialog () {
        SelectPersonFromAdressBookDialog dialog = new SelectPersonFromAdressBookDialog(controller.getAddressbook());

        while (dialog.ok_cancel_option == UNDECIDED){
            waitFor50Milis();
        }
        if (dialog.ok_cancel_option == OK){
            PersonEntry chosenPerson = dialog.getChosenPersonEntry();
            dialog.dispose();
            return chosenPerson;
        } else {
            dialog.dispose();
            return null;
        }

    }

    protected void waitFor50Milis() {
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static JFileChooser createChooseSingleJPEGDialog () {
        return new ChooseSingleJPEGDialog(new File(System.getProperty("user.home")));
    }

}
