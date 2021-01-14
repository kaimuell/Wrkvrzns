package gui.menu;

import controller.Controller;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu(Controller controller) {
        super();
        this.add(new DataMenu(controller));
        this.setVisible(true);
    }
}
