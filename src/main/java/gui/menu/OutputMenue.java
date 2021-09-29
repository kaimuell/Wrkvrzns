package gui.menu;

import controller.Controller;
import gui.dialogFactory.DialogFactory;
import gui.dialogFactory.calculationDialog.CalculationDialog;
import gui.dialogFactory.calculationDialog.CalculationDialogCreatePriceListControllerImplementation;
import model.elements.ArtPieceEntry;
import pdf.DeliveryNote;
import pdf.Portfolio;
import tools.PictureTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputMenue extends JMenu {
    private final JFrame parentFrame;
    private Controller controller;

    public OutputMenue(Controller controller, JFrame parentFrame) {
        super("Ausgabe");
        this.controller = controller;
        this.parentFrame = parentFrame;

        JMenuItem createDeliveryNote = new JMenuItem("Preisliste erstellen");
        createDeliveryNote.setToolTipText("Erstellt eine PDF mit einer Auflistung der ausgewählten Werke");
        createDeliveryNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createList(controller, parentFrame, false);
            }
        });

        JMenuItem createArtpieceList = new JMenuItem("Preisliste erstellen");
        createArtpieceList.setToolTipText("Erstellt eine PDF mit einer Auflistung der ausgewählten Werke");
        createArtpieceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createList(controller, parentFrame, true);
            }
        });

        JMenuItem createArtpieceListWithAdjustedPrices = new JMenuItem("Preisliste mit angepassten Preisen erstellen");
        createArtpieceListWithAdjustedPrices.setToolTipText("Erstellt eine PDF mit einer Auflistung der ausgewählten Werke");
        createArtpieceListWithAdjustedPrices.addActionListener(action -> {
            List<ArtPieceEntry> entries = controller.getSelectedElements();
            if (entries.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Keine Einträge ausgewählt.");
            } else {
                JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
                int option = fileChooser.showOpenDialog(parentFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (file != null && !entries.isEmpty()) {
                        CalculationDialog dialog = new CalculationDialog(parentFrame,
                                entries,
                                new CalculationDialogCreatePriceListControllerImplementation(controller, file));
                    }
                }
            }

        });

        JMenuItem createPortfolio = new JMenuItem("Portfolio erstellen");
        createPortfolio.setToolTipText("Erstellt eine PDF mit einem Portfolio der ausgewählten Werke");
        createPortfolio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ArtPieceEntry> entries = controller.getSelectedElements();
                if (entries.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Keine Einträge ausgewählt");
                } else {
                    JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
                    int option = fileChooser.showOpenDialog(parentFrame);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();

                        if (file != null && !entries.isEmpty()) {
                            List<Image> images = new ArrayList<>();
                            for (ArtPieceEntry entry : entries) {
                                Image pictureOfEntry = controller.loadPictureOf(entry);
                                images.add(pictureOfEntry == null ? PictureTools.defaultEmptyImage() : pictureOfEntry);
                            }
                            try {
                                new Portfolio(entries, images, true).create_PDF(file.getAbsolutePath());
                                JOptionPane.showMessageDialog(parentFrame, "Portfolio erstellt.");
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(parentFrame, "Die Datei konnte nicht erzeugt werden. " +
                                        "Eventuell ist sie in einem anderen Programm geöffnet.");
                            }
                        }
                    }
                }
            }
        });
        this.add(createDeliveryNote);
        this.add(createArtpieceList);
        this.add(createArtpieceListWithAdjustedPrices);
        this.add(createPortfolio);
    }

    private void createList(Controller controller, JFrame parentFrame, boolean withPrices) {
        List<ArtPieceEntry> entries = controller.getSelectedElements();
        if (entries.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Keine Einträge ausgewählt.");
        } else {
            JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
            int option = fileChooser.showOpenDialog(parentFrame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (file != null && !entries.isEmpty()) {
                    try {
                        new DeliveryNote(entries, withPrices).create_PDF(file.getAbsolutePath());
                        JOptionPane.showMessageDialog(parentFrame, "Preisliste erstellt.");
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(parentFrame, "Die Datei konnte nicht erzeugt werden. " +
                                "Eventuell ist sie in einem anderen Programm geöffnet.");
                    }
                }
            }
        }
    }
}