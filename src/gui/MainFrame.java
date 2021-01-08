package gui;

import adressbook.model.ABModel;
import controller.Controller;
import controller.ControllerImplementation;
import model.Model;
import view.ObjectViewPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        super("Wrkvrzns");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,700));

        ABModel adressbook = new ABModel();
        Model model = new Model(adressbook);

        Controller controller = new ControllerImplementation(model, adressbook);

        this.add(new Toolbar(controller), BorderLayout.NORTH);

        ObjectViewPanel panel = new ObjectViewPanel(model, controller);
        this.add (panel, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
    }
}
