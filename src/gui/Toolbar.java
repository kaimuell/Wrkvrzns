package gui;

import controller.Controller;
import controller.dialogController.DialogController;
import gui.elements.FilterChoice;
import gui.elements.FilterType;
import gui.elements.SorterChoice;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Toolbar extends JToolBar{

    private final JFrame parentFrame;
    private final Controller controller;
    private final FilterChoice filterChoice;
    private final SorterChoice sorterChoice;
    private JTextField filterTextField;


    public Toolbar(Controller controller, JFrame parentFrame){
        this.controller = controller;
        this.parentFrame = parentFrame;

        initAddArtPieceButton();
        iniDeleteEntriesButton();
        JLabel filterLabel = new JLabel("  Filtern nach : ");
        this.add (filterLabel);
        this.filterChoice = new FilterChoice();
        this.add(filterChoice);
        this.filterTextField = initFilterTextField();
        this.add(filterTextField);
        JLabel sortlabel = new JLabel(" Sortieren nach : ");
        this.add(sortlabel);
        this.sorterChoice = new SorterChoice();
        this.add(sorterChoice);
        JButton sortButton = new JButton("sortieren");
        sortButton.addActionListener(action -> {
            controller.SortOrFilter().SortBy(sorterChoice.getSortingType());
        });
        this.add(sortButton);
    }

    private void initAddArtPieceButton() {
        JButton addArtPieceButton = new JButton("Werk hinzufügen");
        addArtPieceButton.addActionListener(action ->
                new DialogController(controller).createNewArtPieceDialogThread().start());
        this.add(addArtPieceButton);
    }

    private void iniDeleteEntriesButton() {
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

    private JTextField initFilterTextField() {
        JTextField filterTextField = new JTextField();
        filterTextField.setSize(200, 40);
        filterTextField.setToolTipText("Filtert nach dem im Feld zuvor ausgewählten Element." +
                " Ausgewählt werden alle Einträge, welche die Eingabe enthalten. " +
                "Bestätigen mit Enter");
        filterTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    if (filterChoice.getSelectedFilterType() == FilterType.NAME) {
                        controller.SortOrFilter().filterByName(filterTextField.getText());
                    } else if (filterChoice.getSelectedFilterType() == FilterType.TECHNIQUE){
                        controller.SortOrFilter().filterByTechnique(filterTextField.getText());
                    } else if (filterChoice.getSelectedFilterType() == FilterType.TYPE) {
                        controller.SortOrFilter().filterByType(filterTextField.getText());
                    } else if (filterChoice.getSelectedFilterType() == FilterType.YEAR) {
                        controller.SortOrFilter().filterByYear(filterTextField.getText());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return filterTextField;
    }





}
