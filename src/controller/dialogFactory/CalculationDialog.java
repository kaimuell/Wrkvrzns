package controller.dialogFactory;

import model.elements.ArtPiece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculationDialog extends JDialog {
    private final JTextField taxesField;
    private final JTextField galleryPercentField;
    private final JTextField errorLabel;
    private final RoundingChoice roundingChoice;
    List<ArtPiece> artPieces;
    OkCancelOption okCancelOption;


    public CalculationDialog(Frame owner, List<ArtPiece> artpieces) {
        super(owner);
        this.artPieces = artpieces;
        this.okCancelOption = OkCancelOption.UNDECIDED;

        JPanel centerPanel = new JPanel(new GridLayout(4,2));

        errorLabel = new JTextField("");
        JLabel taxesLabel = new JLabel("Steuersatz aufschlagen : ");
        taxesField = new JTextField();
        JLabel galleryPercentLabel = new JLabel("Galerie Prozente aufschlagen : ");
        galleryPercentField = new JTextField();
        JLabel roundingLabel = new JLabel("Runden auf : ");
        roundingChoice = new RoundingChoice();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(action -> {
            for (ArtPiece artPiece : artpieces) {
                try {
                    artPiece.setPrice(calculatePrice(artPiece.getPrice()));
                    errorLabel.setText("");
                    this.revalidate();
                } catch (Exception e){
                    errorLabel.setText("Konnte Eingaben nicht parsen");
                    this.revalidate();
                }
                //TODO WIrklich Preise im Model ändern?
            }

            okCancelOption = OkCancelOption.OK;
        });
        centerPanel.add(taxesLabel);
        centerPanel.add(taxesField);
        centerPanel.add(galleryPercentLabel);
        centerPanel.add(galleryPercentField);
        centerPanel.add(roundingLabel);
        centerPanel.add(roundingChoice);
        JPanel errorPanel =  new JPanel();
        errorPanel.add(errorLabel);
        this.add(errorPanel);
        this.add(centerPanel);
        this.pack();
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
