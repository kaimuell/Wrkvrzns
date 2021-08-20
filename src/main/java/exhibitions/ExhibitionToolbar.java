package exhibitions;

import exhibitions.model.ExhibitionsModel;
import services.MyToolbar;
import services.MyToolbarButton;

import javax.swing.*;

class ExhibitionToolbar extends MyToolbar {
    private final ExhibitionsController controller;
    private final ExhibitionsModel model;
    private final JFrame owner;

    ExhibitionToolbar(JFrame owner, ExhibitionsController controller, ExhibitionsModel model, boolean withAddingOption) {
        this.owner = owner;
        this.controller = controller;
        this.model = model;
        if (withAddingOption){
            this.add(createAddExhibitionButton());
        }
        this.add(createDeleteSelectedElementButton());
    }

    private JButton createDeleteSelectedElementButton() {
        JButton deleteButton = new MyToolbarButton("Ausgewähle Ausstellung löschen");
        deleteButton.addActionListener(action -> {
            controller.deleteSelectedExhibition();
        });
        return deleteButton;
    }

    private JButton createAddExhibitionButton() {
        JButton addExhibition = new MyToolbarButton("Hinzufügen");
        addExhibition.addActionListener(action -> {
            new ExhibitionViewManager(controller)
                    .createNewExhibitionDialog(owner).start();
        });
        return addExhibition;
    }
}
