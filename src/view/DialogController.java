package view;

import controller.Controller;
import model.ArtPieceEntry;
import model.Model;

import static java.lang.Thread.sleep;

public class DialogController {
    private Controller controller;

    public DialogController(Controller controller) {

        this.controller = controller;

    }

    public Thread createModifyDialogThread(ArtPieceEntry artPieceEntry) {
        return new Thread((Runnable) () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(artPieceEntry, controller);
            while (!dialog.isApproved()) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            controller.modifyEntry(dialog.getArtPieceInfo());
            dialog.dispose();
        });
    }

    public Thread createNewArtPieceDialogThread(){
        return new Thread((Runnable) () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(controller);
            while (!dialog.isApproved()) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            controller.addEntry(dialog.getArtPieceInfo());
            dialog.dispose();
        });
    }

}
