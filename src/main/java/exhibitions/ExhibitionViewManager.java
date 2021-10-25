package exhibitions;

import gui.dialogFactory.OkCancelOption;
import exhibitions.entities.Exhibition;
import model.elements.ArtPieceEntry;

import javax.swing.*;

/**
 * Die Klasse {@link ExhibitionViewManager} fasst die Ansichten auf Ausstellungen zusammen.
 */


public class ExhibitionViewManager {
    ExhibitionsController controller;

    public ExhibitionViewManager(ExhibitionsController controller) {
        this.controller = controller;
    }


    Thread createNewExhibitionDialog (JFrame owner){
        return new Thread ( ()-> {
            Exhibition exhibition = new Exhibition();
            ExhibitionDialog dialog = new ExhibitionDialog(owner, exhibition, true);
            dialog.setVisible(true);
            while (dialog.okCancelOption == OkCancelOption.UNDECIDED) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (dialog.okCancelOption == OkCancelOption.OK) {
                controller.addExhibition(exhibition, false);
            }
            dialog.dispose();
        }
        );
    }

    Thread createEditExhibitionDialog (JFrame owner, Exhibition exhibition, ExhibitionPanel panel, boolean editable){
        return new Thread ( ()-> {
            ExhibitionDialog dialog = new ExhibitionDialog(owner, exhibition, editable);
            dialog.setVisible(true);
            while (dialog.okCancelOption == OkCancelOption.UNDECIDED) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            panel.revalidate();
            dialog.dispose();
        }
        );
    }

    public static void setRelationBetweenArtpieceAndExhibitionDialog(JDialog owner, ArtPieceEntry artPieceEntry){
        ExhibitionViewFrame evf = new ExhibitionViewFrame(true, true, true, null, new CreateRelationEWController(artPieceEntry));
        evf.setVisible(true);
    }

    public static void editExhibitionsOfArtpiece(JDialog owner, ArtPieceEntry entry) {
        ExhibitionViewFrame evf = new ExhibitionViewFrame(false, false, false, entry, new VoidWindowController());
        evf.setLocationRelativeTo(owner);
        evf.setVisible(true);
    }

    public static void createExhibitionMainWindow(JFrame owner){
        ExhibitionViewFrame evf = new ExhibitionViewFrame( true, false, true, null, new VoidWindowController());
        evf.setVisible(true);
        evf.setLocationRelativeTo(owner);
    }
}
