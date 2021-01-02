package view;

import adressbook.model.ABModel;
import controller.ControllerImplementation;
import model.Model;
import model.ModelViewAccess;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        super("Werkverzeichnis");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700,500));

        ABModel adressbook = new ABModel();
        Model model = new Model(adressbook);

        ControllerImplementation controllerImplementation = new ControllerImplementation(model, adressbook);

        ObjectViewPanel panel = new ObjectViewPanel(model, controllerImplementation);
        this.add (panel);

        this.setVisible(true);
        this.pack();
    }
}
