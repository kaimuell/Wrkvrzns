package controller.dialogController;

import adressbook.model.Person;
import controller.Controller;
import model.elements.ArtPieceEntry;

import javax.swing.*;

import java.io.File;

import static controller.dialogController.OkCancelOption.*;
import static java.lang.Thread.sleep;

/**
 * Realisiert Dialog Aufrufe
 */

public class DialogController {
    private final Controller controller;

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
     * Erstellt einen Frame aus dem Adressbuch indem eine Person ausgewählt werden kann
     * und gibt die ausgewählte Person zurück
     * @return die Person / null falls keine gewählt
     */
    public Person selectPersonFromAddressBookDialog () {
            PersonSelectionDialog dialog = new PersonSelectionDialog(controller.getAddressbook());

            while (dialog.getOkCancelOption() == UNDECIDED) {
                waitFor50Milis();
            }
            if (dialog.getOkCancelOption() == OK) {
                Person chosenPerson = dialog.getSelectedPerson();
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

    public static  JFileChooser createChooseSingleProfileDialog(){
        return new ChooseSingleProfileDialog(new File("./profiles/"));
    }

}
