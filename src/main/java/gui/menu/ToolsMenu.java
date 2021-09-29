package gui.menu;

import adressbook.view.AdressBookFrame;
import controller.Controller;
import gui.dialogFactory.calculationDialog.CalculationDialog;
import gui.dialogFactory.calculationDialog.CalculationDialogChangeAllControllerImplementation;
import exhibitions.ExhibitionsController;
import exhibitions.ExhibitionViewManager;
import exhibitions.model.ExhibitionsModel;
import model.ModelContainer;

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
        this.add(new JLabel("-------------------------------"));
        initOpenExhibitions();
        this.add(new JLabel("-------------------------------"));
        initAdjustAllPrices(superComponent, controller);
        initAdjustSelectedPrices(superComponent, controller);

    }

    private void initAdjustAllPrices(JFrame superComponent, Controller controller) {
        JMenuItem adjustPrices = new JMenuItem("Alle Preise anpassen");
        adjustPrices.setToolTipText("Verändert die Preise aller Arbeiten um einen bestimmten Prozentsatz");
        adjustPrices.addActionListener(action -> {
                SwingUtilities.invokeLater(() ->{
                CalculationDialog cd = new CalculationDialog(
                        superComponent,
                        ModelContainer.getModel().getPieces(),
                        new CalculationDialogChangeAllControllerImplementation(controller));
                cd.setVisible(true);
        });
    });
        this.add(adjustPrices);
    }

    private void initAdjustSelectedPrices(JFrame superComponent, Controller controller) {
        JMenuItem adjustPrices = new JMenuItem("Preise der Auswahl anpassen");
        adjustPrices.setToolTipText("Verändert die Preise der ausgewählten Arbeiten um einen bestimmten Prozentsatz");
        adjustPrices.addActionListener(action -> {
            SwingUtilities.invokeLater(() ->{
                CalculationDialog cd = new CalculationDialog(
                        superComponent,
                        controller.getSelectedElements(),
                        new CalculationDialogChangeAllControllerImplementation(controller));
                cd.setVisible(true);
            });
        });
        this.add(adjustPrices);
    }

    private void initOpenExhibitions() {
        JMenuItem openExhibitions = new JMenuItem("Ausstellungen");
        openExhibitions.setToolTipText("Zeigt die Liste der Ausstellungen");
        openExhibitions.addActionListener(action -> {
                    new Thread(() -> {
                       ExhibitionsModel exhibitionsModel = ModelContainer.getModel().getExhibitions();
                        ExhibitionViewManager edc = new ExhibitionViewManager(new ExhibitionsController(exhibitionsModel));
                        edc.createExhibitionMainWindow(exhibitionsModel);
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
            new AdressBookFrame(ModelContainer.getModel().getAdressbook());
            af.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        });
        this.add(adressbook);
    }
}
