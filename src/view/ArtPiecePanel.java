package view;

import controller.Controller;
import model.ArtPieceEntry;

import javax.swing.*;
import java.awt.*;

/**
 * Implementiert eine Zeile im {@link ObjectViewPanel}
 */
class ArtPiecePanel extends JPanel {
    ArtPieceEntry artPiece;
    Controller controller;


    ArtPiecePanel(ArtPieceEntry artPiece, Controller controller, Color color) {
        setLayout(new GridLayout(0,6));

        this.controller = controller;
        this.artPiece = artPiece;

        this.setBackground(color);
        PanelInputScheme inputScheme = new PanelInputScheme(controller, artPiece);
        this.addMouseListener(inputScheme);
        this.addKeyListener(inputScheme);

        JLabel bitmapLabel = new JLabel(new ImageIcon(artPiece.getBitmap()));
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

        JLabel sizeLabel = new JLabel(artPiece.getMeatDataRepresentation());
        sizeLabel.setHorizontalTextPosition(Label.LEFT);
        sizeLabel.setBackground(color);
        add(sizeLabel);

        JLabel yearLabel = new JLabel(String.valueOf(artPiece.getYear()));
        yearLabel.setHorizontalTextPosition(Label.LEFT);
        yearLabel.setBackground(color);
        add(yearLabel);

        revalidate();

    }
}
