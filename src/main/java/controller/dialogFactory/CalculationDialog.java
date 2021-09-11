package controller.dialogFactory;

import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationDialog extends JDialog {
    private final JTextField taxesField;
    private final JTextField galleryPercentField;
    private final JTextField errorLabel;
    private final RoundingChoice roundingChoice;
    private List<ArtPieceWithNewPrice> pieceWithNewPriceList;
    OkCancelOption okCancelOption;


    public CalculationDialog(Frame owner, ArrayList<ArtPieceEntry> artpieces) {
        super(owner);
        initArtpieceList(artpieces);
        this.okCancelOption = OkCancelOption.UNDECIDED;
        JLabel taxesLabel = new JLabel("Steuersatz aufschlagen : ");
        taxesField = new JTextField();
        JLabel galleryPercentLabel = new JLabel("Galerie Prozente aufschlagen : ");
        galleryPercentField = new JTextField();
        JLabel roundingLabel = new JLabel("Runden auf : ");
        roundingChoice = new RoundingChoice();
        JButton okButton = new JButton("OK");
        JPanel centerPanel = initCenterPanel(taxesLabel, galleryPercentLabel, roundingLabel, okButton);
        this.add(centerPanel);
        JPanel errorPanel =  new JPanel();
        errorLabel = new JTextField("");
        errorPanel.add(errorLabel);
        this.add(errorPanel);
        okButton.addActionListener(action -> {
            parseNumbersAndCalculatePrices();
        });
        this.add(okButton);
        this.pack();
    }


    private JPanel initCenterPanel(JLabel taxesLabel, JLabel galleryPercentLabel, JLabel roundingLabel, JButton okButton) {
        JPanel centerPanel = new JPanel(new GridLayout(4,2));
        centerPanel.add(taxesLabel);
        this.add(taxesLabel);
        centerPanel.add(taxesField);
        centerPanel.add(galleryPercentLabel);
        centerPanel.add(galleryPercentField);
        centerPanel.add(roundingLabel);
        centerPanel.add(roundingChoice);
        return centerPanel;
    }

    private void initArtpieceList(List<ArtPieceEntry> artpieces) {
        this.pieceWithNewPriceList = new ArrayList<>();
        for (ArtPiece a: artpieces
             ) {
            this.pieceWithNewPriceList.add(new ArtPieceWithNewPrice(a, 0));
        }
    }

    private void parseNumbersAndCalculatePrices() {
        for (ArtPieceWithNewPrice artPiece : this.pieceWithNewPriceList) {
            try {
                artPiece.setNewPrice(calculatePrice(artPiece.getArtPiece().getPrice()));
                errorLabel.setText("");
                this.revalidate();
                okCancelOption = OkCancelOption.OK;
            } catch (Exception e){
                errorLabel.setText("Konnte Eingaben nicht parsen");
                this.revalidate();
            }
        }
    }

    public List<ArtPieceWithNewPrice> getArtpiecesWithCalculatedPrices(){
        return pieceWithNewPriceList;
    }



    private int calculatePrice(int price) throws Exception {
            float priceWithTaxes = price + (price * parseNumber(taxesField));
            float priceWithGalleryPercent = priceWithTaxes + (priceWithTaxes * parseNumber(galleryPercentField));

            return roundingChoice.roundByChoice((int)priceWithGalleryPercent);
    }


    private int parseNumber(JTextField numberLabel) throws Exception{
            int number = numberLabel.getText().equals("") ? 0 : Integer.parseInt(numberLabel.getText());
        return number ;
    }
}
