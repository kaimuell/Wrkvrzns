package view;

import controller.Controller;
import controller.DialogController.DialogController;
import model.elements.ArtPieceEntry;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



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
            new DialogController(controller).createModifyEntryDialogThread(artPieceEntry).start();
        }
        if(e.isControlDown()){
            controller.addSelectedElement(artPieceEntry);
        }else{
            controller.setSelectedElementTo(artPieceEntry);
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
