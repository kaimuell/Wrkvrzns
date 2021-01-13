package controller.DialogController;

import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import adressbook.view.AdressBookFrame;

import javax.swing.*;
import java.awt.*;


import static controller.DialogController.OkCancelOption.*;

class SelectPersonFromAdressBookDialog extends AdressBookFrame {

    public OkCancelOption ok_cancel_option;

    SelectPersonFromAdressBookDialog(ABModel adressbook){
        super(adressbook);

        this.ok_cancel_option = UNDECIDED;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Select");
        okButton.addActionListener(e -> {
            ok_cancel_option = OK;
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            ok_cancel_option = CANCEL;
        });
        buttonPanel.add(cancelButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
        this.revalidate();

    }

    public PersonEntry getChosenPersonEntry (){
        return controller.getSelectedPerson();
    }

}
