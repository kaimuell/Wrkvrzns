package gui.menu;

import controller.Controller;
import controller.dialogFactory.DialogFactory;
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

/**
 * Ein Menue zur Auswahl der Ausgabearten
 */

public class OutputMenue extends JMenu {
    private final JFrame parentFrame;
    private Controller controller;

    public OutputMenue(Controller controller, JFrame parentFrame) {
        super("Ausgabe");
        this.controller = controller;
        this.parentFrame = parentFrame;

        JMenuItem createArtpieceList = new JMenuItem("Lieferschein erstellen");
        createArtpieceList.setToolTipText("Erstellt eine PDF mit einer Auflistung der ausgewählten Werke");
        createArtpieceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                                new DeliveryNote(entries).create_PDF(file.getAbsolutePath());
                                JOptionPane.showMessageDialog(parentFrame, "Lieferschein erstellt.");
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(parentFrame, "Die Datei konnte nicht erzeugt werden. " +
                                        "Eventuell ist sie in einem anderen Programm geöffnet.");
                            }
                        }
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
                                new Portfolio(entries, images).create_PDF(file.getAbsolutePath());
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
        this.add(createArtpieceList);
        this.add(createPortfolio);
    }
}
