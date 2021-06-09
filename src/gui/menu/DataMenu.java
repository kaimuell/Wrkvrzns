package gui.menu;

import controller.Controller;
import controller.dialogFactory.DialogFactory;

import javax.swing.*;
import java.io.File;

class DataMenu extends JMenu {
    private final JFrame parentFrame;
    private final Controller controller;


    DataMenu(JFrame parentFrame, Controller controller) {
        super("Datei");
        this.parentFrame = parentFrame;
        this.controller = controller;
        JMenuItem newProfile = newProfileOption();
        JMenuItem save = saveOption();
        JMenuItem close = closeOption();
        JMenuItem load = loadOption();
        JMenuItem saveAs = saveAsOption();

        this.add(newProfile);
        this.add(load);
        this.add(save);
        this.add(saveAs);
        this.add(close);
    }


    private JMenuItem newProfileOption() {
        JMenuItem newProfile = new JMenuItem("Neu");
        newProfile.addActionListener( action -> {
            String name = JOptionPane.showInputDialog(parentFrame, "Name des neuen Profils : ");
            if (name != null) {
                controller.createNewProfile(name);
            }
        });
        return newProfile;
    }

    private JMenuItem loadOption() {
        JMenuItem load = new JMenuItem("Laden");
        load.addActionListener(action -> {
            JFileChooser fileChooser = DialogFactory.createChooseSingleProfileDialog();
            int option = fileChooser.showOpenDialog(parentFrame);
            if (option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                controller.load(file);
            }
        });
        return load;
    }

    private JMenuItem saveOption() {
        JMenuItem save = new JMenuItem("Speichern");
        save.addActionListener(a -> controller.save());
        return save;
    }

    private JMenuItem saveAsOption() {
        JMenuItem saveAs = new JMenuItem("Speichern als");
        saveAs.addActionListener(action -> {
            String profileName = JOptionPane.showInputDialog(parentFrame, "Name des neuen Profils : ");
            if (profileName != null) {
                controller.saveAs(profileName);
            }
        });
        return saveAs;
    }

    private JMenuItem closeOption() {
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
}
