package adressbook.view;

import adressbook.controller.ABController;
import services.MyToolbar;
import services.MyToolbarButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**Toolbar des Addressbuchs**/

public class ABToolbar extends MyToolbar {
    ABController controller;
    JFrame superFrame;
    private JTextField filterTextField;

    public ABToolbar(ABController controller, JFrame superFrame) {
        this.controller = controller;
        this.superFrame = superFrame;
        this.add(initAddButton());
        this.add(initRemoveButton());
        this.add(initFilterTextField());
        this.setFloatable(false);
    }

    private JButton initRemoveButton() {
        JButton removeButton = new MyToolbarButton("selektierten Eintrag Löschen");
        removeButton.addActionListener(e -> controller.deleteSelectedPerson());
        return removeButton;
    }

    private JButton initAddButton() {
        JButton addButton = new MyToolbarButton("Eintrag Hinzufügen");
        addButton.addActionListener(e -> new DialogHandler(controller).createAddPersonDialogThread(superFrame).start());
        return addButton;
    }


    private JTextField initFilterTextField() {
        this.filterTextField = new JTextField();
        filterTextField.setSize(200, 40);
        filterTextField.setToolTipText("Filtert nach dem im Feld zuvor ausgewählten Element." +
                " Ausgewählt werden alle Einträge, welche die Eingabe enthalten. " +
                "Bestätigen mit Enter");
        filterTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() =='\n'){
                    controller.filter(filterTextField.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        return filterTextField;
    }

}
