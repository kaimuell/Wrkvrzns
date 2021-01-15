package gui;

import controller.Controller;
import controller.ControllerImplementation;
import controller.FileHandler.FileHandler;
import gui.menu.MainMenu;
import view.select_view.SelectViewPanel;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        super("Wrkvrzns");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000,700));

        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);

        this.setJMenuBar(new MainMenu(controller));
        this.add(new Toolbar(controller), BorderLayout.NORTH);

        SelectViewPanel panel = new SelectViewPanel(controller);
        controller.addView(panel);

        this.add (panel, BorderLayout.CENTER);

        controller.load();
        this.setVisible(true);
        this.pack();
    }
}
