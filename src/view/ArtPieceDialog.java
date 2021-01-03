package view;

import model.ArtPieceEntry;
import model.elements.ArtPiece;

import javax.swing.*;
import java.awt.*;

public class ArtPieceDialog extends JDialog {
    ArtPieceEntry artPiece;
    boolean isApproved;

    public ArtPieceDialog(ArtPieceEntry artPiece) {
        this.artPiece = artPiece;
        this.isApproved = false;
        this.setPreferredSize(new Dimension(400,400));

        //TODO Felder darstellen
    }
    public ArtPieceDialog() {
        this.artPiece = null;
        this.isApproved = false;
        this.setPreferredSize(new Dimension(400,400));

        //TODO Felder darstellen
    }

    public boolean isApproved() {
        return isApproved;
    }

    public ArtPieceEntry getArtPieceInfo() {
        if (artPiece == null){
            //TODO erschaffe neues Artpiece aus Information
        }
        //TODO Informationen aus Feldern auslesen
        return artPiece;
    }
}