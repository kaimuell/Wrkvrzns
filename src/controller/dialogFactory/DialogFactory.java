package controller.dialogFactory;

import adressbook.model.ABModel;
import adressbook.model.Person;
import adressbook.model.PersonEntry;
import controller.Controller;
import model.elements.ArtPieceEntry;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static controller.dialogFactory.OkCancelOption.*;
import static java.lang.Thread.sleep;

/**
 * Realisiert Dialog Aufrufe
 */

public class DialogFactory {
    private final Controller controller;

    public DialogFactory(Controller controller) {

        this.controller = controller;

    }

    public static JFileChooser createChooseSinglePDFDialog() {
        return  new ChoosePDFDialog(new File("./"));
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

    /**
     * Setzt die Liste auf eine als Adreesbuch in einem Dialog angezeigte Liste.
     * Die Methode ist nicht sehr effektiv, da die Liste 2 mal Kopiert wird,
     * aber ok, da die Zahl der Einträge immer verhältnismässig klein ist.
     * @param persons die Liste
     * @return die evtl. veränderte Liste
     */

    public List<Person> editPeopleDialog (List<Person> persons){
        ABModel model = new ABModel(new ArrayList<Person>(persons));
        PersonSelectionDialog dialog = new PersonSelectionDialog(model);

        while (dialog.getOkCancelOption() == UNDECIDED) {
            waitFor50Milis();
        }
        if (dialog.getOkCancelOption() == OK) {
            List<Person> updatedList = createPersonList(model.getPersonList());
            dialog.dispose();
            return updatedList;
        } else {
            dialog.dispose();
            return persons;
        }
    }

    private List<Person> createPersonList(List<PersonEntry> personList) {
        List<Person> newList = new ArrayList<Person>();
        for (PersonEntry entry: personList) {
            newList.add((Person) entry);
        }
        return newList;
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
