package view.select_view;

import adressbook.model.Person;
import controller.Controller;
import model.elements.ArtPieceEntry;
import view.PanelMouseInputScheme;

import javax.swing.*;
import java.awt.*;

/**
 * Implementiert eine Zeile im {@link SelectViewPanel}
 */
class ArtPiecePanel extends JPanel {
    private final ArtPieceEntry artPiece;
    private final Controller controller;


    ArtPiecePanel(ArtPieceEntry artPiece, Controller controller, Color color, JComponent parent) {
        GridLayout layout = new GridLayout(0, 9);
        layout.setHgap(12);
        layout.minimumLayoutSize(parent);

        setLayout(layout);


        this.controller = controller;
        this.artPiece = artPiece;

        this.setBackground(color);
        PanelMouseInputScheme inputScheme = new PanelMouseInputScheme(controller, artPiece);
        this.addMouseListener(inputScheme);

        JLabel bitmapLabel = new JLabel();
        bitmapLabel.setSize(60, 60);
        if (artPiece.getBitmap() != null){
            bitmapLabel.setIcon(new ImageIcon(artPiece.getBitmap().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        }
        bitmapLabel.setBackground(color);
        add(bitmapLabel);

        JLabel nameLabel = new JLabel(artPiece.getName());
        nameLabel.setHorizontalTextPosition(Label.LEFT);
        nameLabel.setBackground(color);
        add(nameLabel);

        JLabel artpieceTypeLabel = new JLabel(artPiece.getType().toString());
        artpieceTypeLabel.setHorizontalTextPosition(Label.LEFT);
        artpieceTypeLabel.setBackground(color);
        add(artpieceTypeLabel);

        JLabel techniqueLabel = new JLabel(artPiece.getTechnique());
        techniqueLabel.setHorizontalTextPosition(Label.LEFT);
        techniqueLabel.setBackground(color);
        add(techniqueLabel);

        JLabel sizeLabel = new JLabel(artPiece.getSizeRepresentation());
        sizeLabel.setHorizontalTextPosition(Label.LEFT);
        sizeLabel.setBackground(color);
        add(sizeLabel);

        JLabel yearLabel = new JLabel(String.valueOf(artPiece.getYear()));
        yearLabel.setHorizontalTextPosition(Label.RIGHT);
        yearLabel.setBackground(color);
        add(yearLabel);

        JLabel priceLabel = new JLabel(artPiece.getPrice() + " €");
        priceLabel.setHorizontalTextPosition(Label.RIGHT);
        priceLabel.setBackground(color);
        add(priceLabel);

        JLabel editionLabel = new JLabel(artPiece.getEditionRepresentation());
        editionLabel.setHorizontalTextPosition(Label.RIGHT);
        priceLabel.setBackground(color);
        add(editionLabel);

        JLabel buyerLabel = new JLabel(artPiece.getBuyersRepresentation());
        priceLabel.setHorizontalTextPosition(Label.LEFT);
        priceLabel.setBackground(color);
        add(buyerLabel);

        revalidate();
    }

    private String createBuyerRepresentation(Person buyer) {
        StringBuilder builder = new StringBuilder();
        builder.append(buyer.getFamilyName());
        builder.append(", ");
        builder.append(buyer.getFirstName());

        return builder.toString();
    }

    public ArtPieceEntry getArtPiece(){
        return this.artPiece;
    }
}
