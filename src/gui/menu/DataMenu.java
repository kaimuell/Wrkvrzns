package gui.menu;

import controller.Controller;

import javax.swing.*;

class DataMenu extends JMenu {
    private final JFrame parentFrame;
    private final Controller controller;


    DataMenu(JFrame parentFrame, Controller controller) {
        super("Datei");
        this.parentFrame = parentFrame;
        this.controller = controller;
        JMenuItem newProfile = newProfileOption(parentFrame, controller);
        JMenuItem save = saveOption(controller);
        JMenuItem close = closeOption(controller);
        //TODO laden
        //TODO speichern als
        this.add(newProfile);
        this.add(save);
        this.add(close);
    }

    private JMenuItem newProfileOption(JFrame parentFrame, Controller controller) {
        JMenuItem newProfile = new JMenuItem("Neu");
        newProfile.addActionListener( action -> {
            String name = JOptionPane.showInputDialog(parentFrame, "Name des neuen Profils : ");
            controller.createNewProfile(name);
        });
        return newProfile;
    }

    private JMenuItem closeOption(Controller controller) {
        JMenuItem close = new JMenuItem("Beenden");
        close.addActionListener(action -> {
            int returnval = JOptionPane.showConfirmDialog(parentFrame,
                    "Wirklich beenden?", "Beenden", JOptionPane.YES_NO_OPTION);
            if (returnval == JOptionPane.YES_OPTION) {
                controller.save();
                System.exit(0);
            }
        });
        return close;
    }

    private JMenuItem saveOption(Controller controller) {
        JMenuItem save = new JMenuItem("Speichern");
        save.addActionListener(a -> {
            controller.save();
        });
        return save;
    }
}
