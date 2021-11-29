package gui.menu;

import controller.Controller;
import gui.dialogFactory.DialogFactory;
import gui.dialogFactory.calculationDialog.CalculationDialog;
import gui.dialogFactory.calculationDialog.CalculationDialogCreatePriceListControllerImplementation;
import languagePack.LanguagePack;
import languagePack.LanguagePackContainer;
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
        super(LanguagePackContainer.getLanguagePack().getOutputMenueHeader());
        this.controller = controller;
        this.parentFrame = parentFrame;

        JMenuItem createDeliveryNote = new JMenuItem(LanguagePackContainer.getLanguagePack().getOutputMenueCreateDeliveryNode());
        createDeliveryNote.setToolTipText(LanguagePackContainer.getLanguagePack().getOutputMenueCreateDeliveryNodeToolTip());
        createDeliveryNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createList(controller, parentFrame, false);
            }
        });

        JMenuItem createArtpieceList = new JMenuItem(LanguagePackContainer.getLanguagePack().getOutputMenueCreatePriceList());
        createArtpieceList.setToolTipText(LanguagePackContainer.getLanguagePack().getOutputMenueCreatePriceListToolTip());
        createArtpieceList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createList(controller, parentFrame, true);
            }
        });

        JMenuItem createArtpieceListWithAdjustedPrices = new JMenuItem(
                LanguagePackContainer.getLanguagePack().getOutputMenueCreatePriceListAdjPrices());
        createArtpieceListWithAdjustedPrices.setToolTipText(
                LanguagePackContainer.getLanguagePack().getOutputMenueCreatePriceListAdjPricesToolTip());
        createArtpieceListWithAdjustedPrices.addActionListener(action -> {
            List<ArtPieceEntry> entries = controller.getSelectedElements();
            if (entries.isEmpty()) {
                JOptionPane.showMessageDialog(
                        parentFrame,
                        LanguagePackContainer.getLanguagePack().getNoEntriesSelected()
                );
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

        JMenuItem createPortfolio = new JMenuItem(LanguagePackContainer.getLanguagePack().getOutputMenueCreatePortfolio());
        createPortfolio.setToolTipText(LanguagePackContainer.getLanguagePack().getOutputMenueCreatePortfolioToolTip());
        createPortfolio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ArtPieceEntry> entries = controller.getSelectedElements();
                if (entries.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, LanguagePackContainer.getLanguagePack().getNoEntriesSelected());
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
                                JOptionPane.showMessageDialog(parentFrame, LanguagePackContainer.getLanguagePack().getPortfolioCreated());
                            } catch (IOException ioException) {
                                JOptionPane.showMessageDialog(parentFrame, LanguagePackContainer.getLanguagePack().getErrorMessageFileNotMade());
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
            JOptionPane.showMessageDialog(parentFrame, LanguagePackContainer.getLanguagePack().getNoEntriesSelected());
        } else {
            JFileChooser fileChooser = DialogFactory.createChooseSinglePDFDialog();
            int option = fileChooser.showOpenDialog(parentFrame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (file != null && !entries.isEmpty()) {
                    try {
                        new DeliveryNote(entries, withPrices).create_PDF(file.getAbsolutePath());
                        JOptionPane.showMessageDialog(parentFrame, LanguagePackContainer.getLanguagePack().getPriceListMade());
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(
                                parentFrame,
                                LanguagePackContainer.getLanguagePack().getErrorMessageFileNotMade()
                        );
                    }
                }
            }
        }
    }
}