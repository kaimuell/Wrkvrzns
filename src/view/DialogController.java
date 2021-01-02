package view;

import controller.Controller;
import model.ArtPieceEntry;

public class DialogController {
    private Controller controller;

    public DialogController(Controller controller) {
        this.controller = controller;
    }

    public Thread createModifyDialogThread(ArtPieceEntry artPieceEntry) {
        //TODO implementieren
        return null;
    }

    public Thread createNewArtPieceDialogThread(){
        //TODO implementieren
        return null;
    }
}
