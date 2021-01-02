package adressbook.view;

import adressbook.controller.ABController;

import javax.swing.*;

/**Toolbar des Addressbuchs**/

public class ABToolbar extends JToolBar {
    ABController controller;
    JFrame superFrame;

    public ABToolbar(ABController controller, JFrame superFrame) {
        this.controller = controller;
        this.superFrame = superFrame;
        this.add(initAddButton());
        this.add(initRemoveButton());
        this.setFloatable(false);
    }

    private JButton initRemoveButton() {
        JButton removeButton = new JButton("selektierten Eintrag Löschen");
        removeButton.addActionListener(e -> controller.deleteSelectedPerson());
        return removeButton;
    }

    private JButton initAddButton() {
        JButton addButton = new JButton("Eintrag Hinzufügen");
        addButton.addActionListener(e -> new DialogHandler(controller).createAddPersonDialogThread(superFrame).start());
        return addButton;
    }


}
