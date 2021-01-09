package controller.DialogController;

import adressbook.model.PersonEntry;
import controller.Controller;
import model.elements.ArtPieceEntry;

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
        return new Thread((Runnable) () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(artPieceEntry, controller);
            while (!dialog.isApproved()) {
                waitFor100Milis();
            }

            controller.modifyEntry(dialog.getArtPieceInfo());
            dialog.dispose();
        });
    }

    public Thread createNewArtPieceDialogThread(){
        return new Thread((Runnable) () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(controller);
            while (!dialog.isApproved()) {
                waitFor100Milis();
            }

            controller.addEntry(dialog.getArtPieceInfo());
            dialog.dispose();
        });
    }

    /**
     * Erstellt einen Frame aus dem Dressbuch indem eine Person ausgewählt werden kann
     * und gibt die ausgewählte Person zurück
     * @return die Person / null falls keine gewählt
     */
    public PersonEntry selectPersonFromAddressBookDialog () {
        SelectPersonFromAdressBookDialog dialog = new SelectPersonFromAdressBookDialog();

        while (dialog.ok_cancel_option == NOTDECIDED){
            waitFor100Milis();
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

    private void waitFor100Milis() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
