package adressbook.view;

import adressbook.controller.ABController;
import adressbook.model.Address;
import adressbook.model.Person;
import adressbook.model.PersonEntry;
import adressbook.model.PersonType;

import javax.swing.*;


import static java.lang.Thread.sleep;

/**Klasse zum Erzeugen und Managen von Eingabe Dialogen **/

class DialogHandler {
    ABController controller;

    DialogHandler(ABController controller){
        this.controller = controller;
    }

    /**
     * Erstellt einen neuen Thread in dem ein Dialog zum erzeugen einer neuen Person läuft
     *
     * @param superFrame der Frame zu dem der Dialog ausgerichtet werden soll.
     * @return der Thread
     */
    public Thread createAddPersonDialogThread(JFrame superFrame){
        return new Thread(() -> {
            PersonDialog dialog = new PersonDialog(superFrame, createEmptyPerson());
            while (!dialog.isApproved()) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            controller.addPerson(dialog.getPerson());
            dialog.dispose();
        });
    }

    /**
     * Erstellt einen neuen Thread in dem ein Dialog zum modifizieren einer Person läuft
     * @param superFrame der Frame zu dem der Dialog ausgerichtet werden soll
     * @param personEntry der Eintrag der Person
     * @return der Thread
     */
    public Thread createModifyPersonDialogThread(JFrame superFrame, PersonEntry personEntry){
        return new Thread((Runnable) () -> {
            int id = personEntry.getId();
            PersonDialog dialog = new PersonDialog(superFrame, (Person) personEntry);
            while (!dialog.isApproved()) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            controller.modifyPerson(id, dialog.getPerson());
            dialog.dispose();
        });
    }



    private Person createEmptyPerson(){
        return new Person("","","","", new Address("","","","",""), PersonType.UNDEFINED);
    }
}
