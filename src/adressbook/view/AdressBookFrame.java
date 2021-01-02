package adressbook.view;

import adressbook.controller.ABControllerImplementation;
import adressbook.model.ABModel;


import javax.swing.*;
import java.awt.*;

public class AdressBookFrame extends JFrame implements ABViewer{

    public AdressBookFrame() throws HeadlessException {
        super("AdressBuch");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(550, 550));
        ABModel model = new ABModel();
        ABControllerImplementation controller = new ABControllerImplementation(model);
        initViewPanel(model, controller);
        JToolBar toolBar = new ABToolbar(controller, this);
        this.add(toolBar, BorderLayout.NORTH);
        this.setResizable(false);
        controller.addViewAsListener(this);
        pack();
        setVisible(true);
    }

    private void initViewPanel(ABModel model, ABControllerImplementation controller) {
        ABViewPanel viewPanel = new ABViewPanel(model, controller, this);
        JScrollPane scrollPane = new JScrollPane (viewPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        controller.addViewAsListener(viewPanel);
        this.add(scrollPane);
    }


    @Override
    public void refreshView() {
        this.repaint();
    }
}
