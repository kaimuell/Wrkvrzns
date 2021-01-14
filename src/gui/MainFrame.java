package gui;

import controller.Controller;
import controller.ControllerImplementation;
import controller.FileHandler.FileHandler;
import gui.menu.MainMenu;
import view.ObjectViewPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        super("Wrkvrzns");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,700));

        FileHandler fileHAndler = new FileHandler();

        Controller controller = new ControllerImplementation(fileHAndler);

        this.setJMenuBar(new MainMenu(controller));
        this.add(new Toolbar(controller), BorderLayout.NORTH);

        ObjectViewPanel panel = new ObjectViewPanel(controller);
        controller.addView(panel);
        this.add (panel, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
    }
}
