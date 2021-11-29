package gui.menu;

import adressbook.view.AdressBookFrame;
import controller.Controller;
import gui.dialogFactory.calculationDialog.CalculationDialog;
import gui.dialogFactory.calculationDialog.CalculationDialogChangeAllControllerImplementation;
import exhibitions.ExhibitionViewManager;
import languagePack.LanguagePackContainer;
import model.ModelContainer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ToolsMenu extends JMenu {
    private final JFrame superComponent;
    private Controller controller;

    public ToolsMenu(JFrame superComponent, Controller controller) {
        super(LanguagePackContainer.getLanguagePack().getTools());
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
        JMenuItem adjustPrices = new JMenuItem(LanguagePackContainer.getLanguagePack().getToolsMenuAdjustAllPrices());
        adjustPrices.setToolTipText(LanguagePackContainer.getLanguagePack().getToolsMenuAdjustAllPricesToolTipText());
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
        JMenuItem adjustPrices = new JMenuItem(LanguagePackContainer.getLanguagePack().getToolsMenuAdjustPrices());
        adjustPrices.setToolTipText(LanguagePackContainer.getLanguagePack().getToolsMenuAdjustPricesToolTipText());
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
        JMenuItem openExhibitions = new JMenuItem(LanguagePackContainer.getLanguagePack().getToolsMenuOpenExhibitions());
        openExhibitions.setToolTipText(LanguagePackContainer.getLanguagePack().getToolsMenuOpenExhibitionsToolTipText());
        openExhibitions.addActionListener(action -> {
                     SwingUtilities.invokeLater(()->{
                        ExhibitionViewManager.createExhibitionMainWindow(superComponent);
                    });
                }
        );
        this.add(openExhibitions);

    }

    private void initImportContacts(JFrame superComponent, Controller controller) {
        JMenuItem importAdresses = new JMenuItem(LanguagePackContainer.getLanguagePack().getToolsMenuImportAdresses());
        importAdresses.setToolTipText(LanguagePackContainer.getLanguagePack().getToolsMenuImportAdressesToolTipText());
        importAdresses.addActionListener(action -> {
            JFileChooser chooser = createFilechooserforCSVFiles();
            int chooseOption = chooser.showOpenDialog(superComponent);
            if (chooseOption == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                int paneOption = JOptionPane.showConfirmDialog(superComponent,
                        LanguagePackContainer.getLanguagePack().getToolsMenuImportAdressesConfimDialogText());
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
        JMenuItem adressbook = new JMenuItem(LanguagePackContainer.getLanguagePack().getToolsMenuOpenAddressBook());
        adressbook.addActionListener(action -> {
            AdressBookFrame af =
            new AdressBookFrame(ModelContainer.getModel().getAdressbook());
            af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
        this.add(adressbook);
    }
}
