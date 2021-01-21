package gui;

import controller.Controller;
import controller.ControllerImplementation;
import controller.fileHandler.FileHandler;
import gui.menu.MainMenuBar;
import view.ViewHub;
import view.ViewOption;
import view.pictureView.PictureView;
import view.select_view.SelectViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Das zentrale Fenster zur Anzeige des Programms
 */

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Wrkvrzns");
        setPreferredSize(new Dimension(1000,700));

        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);



        SelectViewPanel selectVIew = new SelectViewPanel(controller);
        PictureView pictureView = new PictureView(controller);
        ViewHub viewHub = new ViewHub(pictureView,selectVIew, ViewOption.SELECT_VIEW, controller);
        controller.addView(viewHub);

        this.add (new JScrollPane(viewHub, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        this.setJMenuBar(new MainMenuBar(this, controller, viewHub));
        this.add(new Toolbar(controller, this), BorderLayout.NORTH);

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
