package gui.dialogFactory.calculationDialog;

import gui.dialogFactory.OkCancelOption;
import gui.dialogFactory.RoundingChoice;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationDialog extends JDialog {
    private final CalculationDialogController dialogController;
    private JTextField percentField;
    private JTextField errorLabel;
    private RoundingChoice roundingChoice;
    private List<ArtPieceWithNewPrice> pieceWithNewPriceList;

    public CalculationDialog(Frame owner, List<ArtPieceEntry> artpieces, CalculationDialogController dialogController) {
        super(owner);
        this.setPreferredSize(new Dimension(400, 200));
        initArtpieceList(artpieces);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dialogController = dialogController;
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = initCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        JPanel errorPanel = new JPanel();
        errorLabel = new JTextField("");
        errorPanel.add(errorLabel);
        mainPanel.add(errorPanel, BorderLayout.NORTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(action -> {
            parseNumbersAndCalculatePrices();
        });
        JButton cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(action -> {
            cancel();
        });
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void cancel() {
        dialogController.edit(OkCancelOption.CANCEL, null);
        this.dispose();
    }


    private JPanel initCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(3, 2));
        JLabel percentLabel = new JLabel("Prozente aufschlagen : ");
        percentField = new JTextField(10);
        JLabel infoLabel = new JLabel(" - für abziehen");
        JLabel roundingLabel = new JLabel("Runden auf : ");
        roundingChoice = new RoundingChoice();
        centerPanel.add(percentLabel);
        centerPanel.add(percentField);
        centerPanel.add(infoLabel);
        centerPanel.add(new JLabel());
        centerPanel.add(roundingLabel);
        centerPanel.add(roundingChoice);
        return centerPanel;
    }

    private void initArtpieceList(List<ArtPieceEntry> artpieces) {
        this.pieceWithNewPriceList = new ArrayList<>();
        for (ArtPieceEntry a : artpieces
        ) {
            this.pieceWithNewPriceList.add(new ArtPieceWithNewPrice(a, 0));
        }
    }

    private void parseNumbersAndCalculatePrices() {
        for (ArtPieceWithNewPrice artPiece : this.pieceWithNewPriceList) {
            try {
                artPiece.setNewPrice(calculatePrice(artPiece.getArtPiece().getPrice()));
            } catch (Exception e) {
                errorLabel.setText("Konnte Eingaben nicht parsen");
                this.revalidate();
            }
        }

        dialogController.edit(OkCancelOption.OK, pieceWithNewPriceList);
        this.dispose();
    }

    private int calculatePrice(int price) throws Exception {
        float adjustedPrices = price + (price * parseNumber(percentField)/100);
        return roundingChoice.roundByChoice((int) adjustedPrices);
    }


    private int parseNumber(JTextField numberLabel) throws Exception {
        int number = numberLabel.getText().equals("") ? 0 : Integer.parseInt(numberLabel.getText());
        return number;
    }
}
