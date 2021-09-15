package controller.dialogFactory;

import model.elements.ArtPiece;
import model.elements.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationDialog extends JDialog {
    private  JTextField taxesField;
    private  JTextField galleryPercentField;
    private  JTextField errorLabel;
    private  RoundingChoice roundingChoice;
    private List<ArtPieceWithNewPrice> pieceWithNewPriceList;
    private OkCancelOption okCancelOption;


    public CalculationDialog(Frame owner, ArrayList<ArtPieceEntry> artpieces) {
        super(owner);
        this.setPreferredSize(new Dimension(400, 200));
        initArtpieceList(artpieces);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.okCancelOption = OkCancelOption.UNDECIDED;
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = initCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        JPanel errorPanel =  new JPanel();
        errorLabel = new JTextField("");
        errorPanel.add(errorLabel);
        mainPanel.add(errorPanel,BorderLayout.NORTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(action -> {
            parseNumbersAndCalculatePrices();
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }


    private JPanel initCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(3,2));
        JLabel taxesLabel = new JLabel("Steuersatz aufschlagen : ");
        taxesField = new JTextField(10);
        JLabel galleryPercentLabel = new JLabel("Galerie Prozente aufschlagen : ");
        galleryPercentField = new JTextField(10);
        JLabel roundingLabel = new JLabel("Runden auf : ");
        roundingChoice = new RoundingChoice();
        centerPanel.add(taxesLabel);
        centerPanel.add(taxesField);
        centerPanel.add(galleryPercentLabel);
        centerPanel.add(galleryPercentField);
        centerPanel.add(roundingLabel);
        centerPanel.add(roundingChoice);
        return centerPanel;
    }

    private void initArtpieceList(List<ArtPieceEntry> artpieces) {
        this.pieceWithNewPriceList = new ArrayList<>();
        for (ArtPieceEntry a: artpieces
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

    public OkCancelOption getOkCancelOption() {
        return okCancelOption;
    }
}
