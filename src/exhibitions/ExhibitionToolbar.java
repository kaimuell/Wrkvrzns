package exhibitions;

import exhibitions.controller.ExhibitionsController;
import exhibitions.model.ExhibitionsModel;

import javax.swing.*;

class ExhibitionToolbar extends JToolBar{
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
        JButton deleteButton = new JButton("Ausgewähle Ausstellung löschen");
        deleteButton.addActionListener(action -> {
            controller.deleteSelectedExhibition();
        });
        return deleteButton;
    }

    private JButton createAddExhibitionButton() {
        JButton addExhibition = new JButton("Hinzufügen");
        addExhibition.addActionListener(action -> {
            new ExhibitionDialogController(controller)
                    .createNewExhibitionDialog(owner).start();
        });
        return addExhibition;
    }
}
