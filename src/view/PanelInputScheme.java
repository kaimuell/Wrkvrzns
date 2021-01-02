package view;

import controller.Controller;
import model.ArtPieceEntry;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class PanelInputScheme implements MouseListener, KeyListener {
    boolean CtrlHold;
    boolean ShiftHold;
    ArtPieceEntry artPieceEntry;
    Controller controller;

    public PanelInputScheme(Controller controller, ArtPieceEntry artPiece) {
        this.controller = controller;
        this.artPieceEntry = artPiece;
        this.CtrlHold = false;
        this.ShiftHold = false;
    }
    //TODO Testen ob funktioniert obwohl verschiedene Panel inputschemes, funktionieren booleans richtig???

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isShiftDown()){
            ShiftHold = true;
        }
        if(e.isControlDown()){
            CtrlHold = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.isShiftDown()){
            ShiftHold =false;
        }
        if (e.isControlDown()){
            CtrlHold = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            new DialogController(controller).createModifyDialogThread(artPieceEntry).start();
        }
        if(ShiftHold){
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
