package gui.menu;

import controller.Controller;
import gui.dialogFactory.DialogFactory;
import languagePack.LanguagePackContainer;

import javax.swing.*;
import java.io.File;

class DataMenu extends JMenu {
    private final JFrame parentFrame;
    private final Controller controller;


    DataMenu(JFrame parentFrame, Controller controller) {
        super(LanguagePackContainer.getLanguagePack().getDataMenuHeader());
        this.parentFrame = parentFrame;
        this.controller = controller;
        JMenuItem newProfile = newProfileOption();
        JMenuItem save = saveOption();
        JMenuItem close = closeOption();
        JMenuItem load = loadOption();
        //JMenuItem saveAs = saveAsOption();

        this.add(newProfile);
        this.add(load);
        this.add(save);
        //this.add(saveAs);
        this.add(close);
    }


    private JMenuItem newProfileOption() {
        JMenuItem newProfile = new JMenuItem(LanguagePackContainer.getLanguagePack().getNew());
        newProfile.addActionListener( action -> {
            String name = JOptionPane.showInputDialog(parentFrame, LanguagePackContainer.getLanguagePack().getDataMenuNewProfile());
            if (name != null) {
                controller.createNewProfile(name);
            }
        });
        return newProfile;
    }

    private JMenuItem loadOption() {
        JMenuItem load = new JMenuItem(LanguagePackContainer.getLanguagePack().getDataMenuLoad());
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
        JMenuItem save = new JMenuItem(LanguagePackContainer.getLanguagePack().getDataMenuSave());
        save.addActionListener(a -> controller.save());
        return save;
    }

    private JMenuItem saveAsOption() {
        JMenuItem saveAs = new JMenuItem(LanguagePackContainer.getLanguagePack().getDataMenuSaveAs());
        saveAs.addActionListener(action -> {
            String profileName = JOptionPane.showInputDialog(parentFrame, LanguagePackContainer.getLanguagePack().getDataMenuSaveAsDialog());
            if (profileName != null) {
                controller.saveAs(profileName);
            }
        });
        return saveAs;
    }

    private JMenuItem closeOption() {
        JMenuItem close = new JMenuItem(LanguagePackContainer.getLanguagePack().getQuit());
        close.addActionListener(action -> {
            int returnval = JOptionPane.showConfirmDialog(
                    parentFrame,
                    LanguagePackContainer.getLanguagePack().getQuitConfimQuestion(),
                    LanguagePackContainer.getLanguagePack().getQuit(),
                    JOptionPane.YES_NO_OPTION
            );
            if (returnval == JOptionPane.YES_OPTION) {
                controller.save();
                System.exit(0);
            }
        });
        return close;
    }
}
