package gui;

import controller.Controller;
import controller.DialogController.DialogController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JToolBar{

    private Controller controller;

    public Toolbar(Controller controller){
        this.controller = controller;

        initAddArtPieceButton(controller);
    }

    private void initAddArtPieceButton(Controller controller) {
        JButton addArtPieceButton = new JButton("Werk hinzufügen");
        addArtPieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogController(controller).createNewArtPieceDialogThread().start();
            }
        });
        this.add(addArtPieceButton);
    }
}
