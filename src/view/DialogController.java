package view;

import controller.Controller;
import model.ArtPieceEntry;
import model.Model;

import static java.lang.Thread.sleep;

public class DialogController {
    private Controller controller;
    private Model model;

    public DialogController(Controller controller, Model model) {

        this.controller = controller;
        this.model = model;
    }

    public Thread createModifyDialogThread(ArtPieceEntry artPieceEntry) {
        return new Thread((Runnable) () -> {
            ArtPieceDialog dialog = new ArtPieceDialog(artPieceEntry, model);
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
            ArtPieceDialog dialog = new ArtPieceDialog(model);
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
