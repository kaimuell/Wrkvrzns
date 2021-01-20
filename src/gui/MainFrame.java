package gui;

import controller.Controller;
import controller.ControllerImplementation;
import controller.fileHandler.FileHandler;
import gui.menu.MainMenu;
import view.select_view.SelectViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainFrame extends JFrame {
    public MainFrame() {
        super("Wrkvrzns");
        setPreferredSize(new Dimension(1000,700));

        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);

        this.setJMenuBar(new MainMenu(controller));
        this.add(new Toolbar(controller, this), BorderLayout.NORTH);

        SelectViewPanel panel = new SelectViewPanel(controller);
        controller.addView(panel);

        this.add (new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        controller.load();


        //Speichert bei Schliessen des Programms
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.save();
                System.exit(0);
            }
        });
        this.setVisible(true);
        this.pack();
    }
}
