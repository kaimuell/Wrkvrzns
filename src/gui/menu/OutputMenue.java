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

                JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
                int option = fileChooser.showOpenDialog(parentFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (file != null) {
                        List<ArtPieceEntry> entries = controller.getSelectedElements();
                        try {
                            new DeliveryNote(entries).create_PDF(file.getAbsolutePath());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            //TODO Exception handling, Testen
                        }
                    }
                }
            }
        });
        JMenuItem createPortfolio = new JMenuItem("Portfolio erstellen");
        createArtpieceList.setToolTipText("Erstellt eine PDF mit einem Portfolio der ausgewählten Werke");
        createArtpieceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
                int option = fileChooser.showOpenDialog(parentFrame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (file != null) {
                        List<ArtPieceEntry> entries = controller.getSelectedElements();
                        List<Image> images = new ArrayList<>();
                        for (ArtPieceEntry entry : entries) {
                            Image pictureOfEntry = controller.loadPictureOf(entry);
                            images.add(pictureOfEntry == null ? PictureTools.defaultEmptyImage() : pictureOfEntry);
                        }
                        try {
                            new Portfolio(entries, images).create_PDF(file.getAbsolutePath());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            //TODO Exception handling, Testen
                        }
                    }
                }
            }
        });
        this.add(createArtpieceList);
        this.add(createPortfolio);
    }
}
