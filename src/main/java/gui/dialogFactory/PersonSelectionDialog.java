package gui.dialogFactory;

import adressbook.model.ABModel;
import adressbook.model.Person;
import adressbook.view.AdressBookFrame;
import languagePack.LanguagePackContainer;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog zur Auswahl einer Person aus dem Adressbuch
 */
public class PersonSelectionDialog extends AdressBookFrame {

    private Person selectedPerson;
    private OkCancelOption okCancelOption;

    public PersonSelectionDialog(ABModel model){
     super(model);
     this.okCancelOption = OkCancelOption.UNDECIDED;
     this.selectedPerson = null;
     this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     JPanel buttonPanel = new JPanel();
     JButton okButton = new JButton(LanguagePackContainer.getLanguagePack().getOk());
     okButton.addActionListener(action -> {
         this.okCancelOption = OkCancelOption.OK;
         this.selectedPerson = controller.getSelectedPerson();
     });
     JButton cancelButton = new JButton(LanguagePackContainer.getLanguagePack().getCancel());
     cancelButton.addActionListener(action -> {
         this.okCancelOption = OkCancelOption.CANCEL;
     });
     buttonPanel.add(okButton);
     buttonPanel.add(cancelButton);
     this.add(buttonPanel, BorderLayout.SOUTH);
     this.setVisible(true);
     this.revalidate();
     refreshView();
 }

    public OkCancelOption getOkCancelOption() {

        return okCancelOption;

    }
    public Person getSelectedPerson() {

        return selectedPerson;
    }
}

