package gui.menu;

import controller.Controller;

import javax.swing.*;

class DataMenu extends JMenu {
    Controller controller;

    DataMenu(Controller controller) {
        super("Datei");

        JMenuItem save = new JMenuItem("Speichern");
        save.addActionListener(a -> {
            controller.save();
        });

        this.add(save);
    }
}
