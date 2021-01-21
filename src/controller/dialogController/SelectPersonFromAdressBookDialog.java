package controller.dialogController;

import adressbook.controller.ABController;
import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;
import adressbook.model.PersonEntry;
import adressbook.view.ABToolbar;
import adressbook.view.ABViewPanel;
import adressbook.view.ABViewer;

import javax.swing.*;
import java.awt.*;

//TODO funktioniert nicht!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import static controller.dialogController.OkCancelOption.*;

class SelectPersonFromAdressBookDialog extends JFrame implements ABViewer {

    private ABController controller;
    private JPanel mainPanel;

    public OkCancelOption ok_cancel_option;

    SelectPersonFromAdressBookDialog(ABModel adressbook){
        super("Adressbuch");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setPreferredSize(new Dimension(550, 650));

        this.mainPanel = new JPanel();
        ABModel model = adressbook;
        this.controller = new ABControllerImplementation(model);
        initViewPanel(model, controller);
        JToolBar toolBar = new ABToolbar(controller, this);
        this.add(toolBar, BorderLayout.NORTH);
        this.setResizable(false);
        controller.addViewAsListener(this);
        pack();
        setVisible(true);

        this.ok_cancel_option = UNDECIDED;

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Auswählen");
        okButton.addActionListener(e -> {
            ok_cancel_option = OK;
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(e -> {
            ok_cancel_option = CANCEL;
        });
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.pack();
        this.revalidate();

    }

    private void initViewPanel(ABModel model, ABController controller) {
        ABViewPanel viewPanel = new ABViewPanel(model, controller, this);
        JScrollPane scrollPane = new JScrollPane (viewPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        controller.addViewAsListener(viewPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public PersonEntry getChosenPersonEntry (){
        return controller.getSelectedPerson();
    }

    @Override
    public void refreshView() {
        this.repaint();
    }
}
