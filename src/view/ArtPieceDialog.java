package view;

import model.elements.ArtPiece;

import javax.swing.*;
import java.awt.*;

public class ArtPieceDialog extends JDialog {
    ArtPiece artPiece;
    boolean isApproved;

    public ArtPieceDialog(ArtPiece artPiece) {
        this.artPiece = artPiece;
        this.isApproved = false;
        this.setPreferredSize(new Dimension(400,400));


    }
}