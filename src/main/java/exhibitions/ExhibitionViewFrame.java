package exhibitions;

import controller.dialogFactory.OkCancelOption;
import exhibitions.model.ExhibitionsModel;

import javax.swing.*;
import java.awt.*;

/**
 * Fenster zum Anzeigen einer Liste von Ausstellungen
 */

class ExhibitionViewFrame extends JFrame implements ExhibitionView {

    public OkCancelOption okCancelOption;

    /**
     *
     * @param model             Das ExhibitionsModel
     * @param withAddingOption  können Ausstellungen hinzugefügt werden?
     * @param withCancelButton  hat das Fenster einen Button zum Abbrechen?
     * @param isEditable        sind die Ausstellungen editierbar?
     */
    ExhibitionViewFrame(ExhibitionsModel model, boolean withAddingOption, boolean withCancelButton, boolean isEditable) {
        super("Ausstellungen");
        ExhibitionsController controller = new ExhibitionsController(model);
        this.okCancelOption = OkCancelOption.UNDECIDED;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(500, 500));

        ExhibitionToolbar toolbar = new ExhibitionToolbar(this, controller, model, withAddingOption);
        this.add(toolbar, BorderLayout.NORTH);
        ExhibitionPanelList epl = new ExhibitionPanelList(this, controller, model, isEditable);
        this.add(epl, BorderLayout.CENTER);
        controller.addView(epl);
        controller.addView(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton okButton =  new JButton("OK");
        okButton.addActionListener(action -> this.okCancelOption = OkCancelOption.OK);
        buttonPanel.add(okButton);

        if (withCancelButton){
            JButton cancelButton = new JButton("ABBRECHEN");
            cancelButton.addActionListener(action -> okCancelOption = OkCancelOption.CANCEL);
            buttonPanel.add(cancelButton);
        }
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void refreshView() {
        revalidate();
        repaint();
    }
}
