package gui;

import controller.Controller;
import controller.dialogController.DialogController;

import javax.swing.*;


public class Toolbar extends JToolBar{

    private final JFrame parentFrame;
    private Controller controller;

    public Toolbar(Controller controller, JFrame parentFrame){
        this.controller = controller;
        this.parentFrame = parentFrame;

        initAddArtPieceButton(controller);
        iniDeleteEntriesButton(controller);
    }

    private void initAddArtPieceButton(Controller controller) {
        JButton addArtPieceButton = new JButton("Werk hinzufügen");
        addArtPieceButton.addActionListener(action ->
                new DialogController(controller).createNewArtPieceDialogThread().start());
        this.add(addArtPieceButton);
    }

    private void iniDeleteEntriesButton(Controller controller) {
        JButton deleteEntryButton = new JButton("Markierte Einträge löschen");
        deleteEntryButton.addActionListener(action -> {
            int returnval = JOptionPane.showConfirmDialog(parentFrame, "Einträge wirklich löschen?",
                    "Bestätigen", JOptionPane.YES_NO_OPTION);
            if (returnval == JOptionPane.YES_OPTION) {
                controller.deleteSelectedElements();
            }
        });
        this.add(deleteEntryButton);
    }
}
