package gui.menu;

import adressbook.view.AdressBookFrame;
import controller.Controller;
import controller.dialogController.OkCancelOption;
import exhibitions.gui.ExhibitionViewFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ToolsMenu extends JMenu {
    private final JFrame superComponent;
    private Controller controller;

    public ToolsMenu(JFrame superComponent, Controller controller) {
        super("Werkzeuge");
        this.controller = controller;
        this.superComponent = superComponent;
        initOpenAdressbook();
        initImportContacts(superComponent, controller);
        initOpenExhibitions();
    }

    private void initOpenExhibitions() {
        JMenuItem openExhibitions = new JMenuItem("Ausstellungen");
        openExhibitions.setToolTipText("Zeigt die Liste der Ausstellungen");
        openExhibitions.addActionListener(action -> {
                    new Thread(() -> {
                        ExhibitionViewFrame evf = new ExhibitionViewFrame(controller.getModel().exhibitions, true, false);
                        evf.setVisible(true);
                        while (evf.okCancelOption == OkCancelOption.UNDECIDED) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        evf.dispose();
                    }).start();
                }
        );
        this.add(openExhibitions);

    }

    private void initImportContacts(JFrame superComponent, Controller controller) {
        JMenuItem importAdresses = new JMenuItem("Kontakte importieren");
        importAdresses.setToolTipText("Benötogt eine von Thunderbird exportierte .csv Datei, " +
                "bei der die Einträge durch Komma getrennt sind.");
        importAdresses.addActionListener(action -> {
            JFileChooser chooser = createFilechooserforCSVFiles();
            int chooseOption = chooser.showOpenDialog(superComponent);
            if (chooseOption == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                int paneOption = JOptionPane.showConfirmDialog(superComponent,
                        "Nur Kontakte importieren, die einen gespeicherten Namen haben?");
                if (paneOption == JOptionPane.OK_OPTION){
                    controller.importContacts(file, true);
                }else if(paneOption == JOptionPane.NO_OPTION){
                    controller.importContacts(file, true);
                }
            }
        });
        add(importAdresses);
    }

    private JFileChooser createFilechooserforCSVFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV", "csv"));
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setMultiSelectionEnabled(false);
        return chooser;
    }

    private void initOpenAdressbook() {
        JMenuItem adressbook = new JMenuItem("Adressbuch öffnen");
        adressbook.addActionListener(action -> {
            AdressBookFrame af =
            new AdressBookFrame(controller.getAddressbook());
            af.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        });
        this.add(adressbook);
    }
}
