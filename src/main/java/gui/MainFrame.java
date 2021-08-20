package gui;

import controller.Controller;
import controller.ControllerImplementation;
import controller.fileHandler.FileHandler;
import gui.menu.MainMenuBar;
import model.Model;
import view.ViewHub;
import view.ViewOption;
import view.Viewer;
import view.pictureView.PictureView;
import view.select_view.SelectViewPanel;
import view.tableView.TableView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Das zentrale Fenster zur Anzeige des Programms
 */

public class MainFrame extends JFrame implements Viewer {

    public MainFrame() {
        super("Wrkvrzns");
        setPreferredSize(new Dimension(1000,700));

        FileHandler fileHandler = new FileHandler();
        Controller controller = new ControllerImplementation(fileHandler);

        ViewHub viewHub = initaliseViews(controller);

        this.setJMenuBar(new MainMenuBar(this, controller, viewHub));
        this.add(new Toolbar(controller, this), BorderLayout.NORTH);

        NotificationField notificationField = new NotificationField();
        controller.addMessageBord(notificationField);
        this.add(notificationField, BorderLayout.SOUTH);

        //laden der vorigen Datei
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

    private ViewHub initaliseViews(Controller controller) {
        SelectViewPanel selectView = new SelectViewPanel(controller);
        PictureView pictureView = new PictureView(controller);
        TableView tableView = new TableView();
        ViewHub viewHub = new ViewHub(this, pictureView,selectView, tableView, ViewOption.SELECT_VIEW, controller);
        controller.addView(viewHub);
        controller.addView(this);
        this.add (new JScrollPane(viewHub, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        return viewHub;
    }

    @Override
    public void refreshView() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void changeBackgroundOfSelectedElements() {

    }

    @Override
    public void setModelTo(Model model) {

    }
}
