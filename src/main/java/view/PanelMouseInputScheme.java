package view;

import controller.Controller;
import controller.dialogFactory.DialogFactory;
import model.elements.ArtPieceEntry;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Standard MousseInputs auf einem ViewPanel
 */
public class PanelMouseInputScheme implements MouseListener {

    private ArtPieceEntry artPieceEntry;
    private Controller controller;

    public PanelMouseInputScheme(Controller controller, ArtPieceEntry artPiece) {
        this.controller = controller;
        this.artPieceEntry = artPiece;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            new DialogFactory(controller).createModifyEntryDialogThread(artPieceEntry).start();
        }
        if(e.isControlDown()){
            controller.selectAdditionalElement(artPieceEntry);
        }else{
            controller.selectElement(artPieceEntry);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
