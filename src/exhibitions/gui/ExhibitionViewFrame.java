package exhibitions.gui;

import controller.dialogController.OkCancelOption;
import exhibitions.controller.ExhibitionsController;
import exhibitions.model.ExhibitionsModel;

import javax.swing.*;
import java.awt.*;

public class ExhibitionViewFrame extends JFrame implements ExhibitionView {

    private ExhibitionsModel model;
    private ExhibitionsController controller;
    public OkCancelOption okCancelOption;

    /**
     *
     * @param model
     * @param withAddingOption
     * @param withCancelButton
     */
    public ExhibitionViewFrame(ExhibitionsModel model, boolean withAddingOption, boolean withCancelButton) {
        super("Ausstellungen");
        this.model = model;
        this.controller = new ExhibitionsController(model);
        this.okCancelOption = OkCancelOption.UNDECIDED;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(500, 500));

        ExhibitionToolbar toolbar = new ExhibitionToolbar(this, controller, model, withAddingOption);
        this.add(toolbar, BorderLayout.NORTH);
        ExhibitionPanelList epl = new ExhibitionPanelList(this, controller, model);
        this.add(epl, BorderLayout.CENTER);
        controller.addView(epl);
        controller.addView(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton okButton =  new JButton("OK");
        okButton.addActionListener(action -> {
            this.okCancelOption = OkCancelOption.OK;
        });
        buttonPanel.add(okButton);

        if (withCancelButton){
            JButton cancelButton = new JButton("ABBRECHEN");
            cancelButton.addActionListener(action -> {
                okCancelOption = OkCancelOption.CANCEL;
            });
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
