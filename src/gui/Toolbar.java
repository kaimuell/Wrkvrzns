package gui;

import controller.Controller;
import controller.DialogController.DialogController;

import javax.swing.*;


public class Toolbar extends JToolBar{

    private Controller controller;

    public Toolbar(Controller controller){
        this.controller = controller;

        initAddArtPieceButton(controller);
        iniDeleteEntriesButton(controller);
    }

    private void iniDeleteEntriesButton(Controller controller) {
        JButton deleteEntryButton = new JButton("Markierte Einträge löschen");
        deleteEntryButton.addActionListener(action -> {
            controller.deleteSelectedElements();
        });
        this.add(deleteEntryButton);
    }

    private void initAddArtPieceButton(Controller controller) {
        JButton addArtPieceButton = new JButton("Werk hinzufügen");
        addArtPieceButton.addActionListener(action ->
                new DialogController(controller).createNewArtPieceDialogThread().start());
        this.add(addArtPieceButton);
    }
}
