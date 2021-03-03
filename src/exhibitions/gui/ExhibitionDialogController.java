package exhibitions.gui;

import controller.dialogController.OkCancelOption;
import exhibitions.controller.ExhibitionsController;
import exhibitions.model.Exhibition;
import exhibitions.model.ExhibitionsModel;

import javax.swing.*;

public class ExhibitionDialogController {
    ExhibitionsController controller;

    public ExhibitionDialogController(ExhibitionsController controller) {
        this.controller = controller;
    }


    Thread createNewExhibitionDialog (JFrame owner){
        return new Thread ( ()-> {
            Exhibition exhibition = new Exhibition();
            ExhibitionDialog dialog = new ExhibitionDialog(owner, exhibition);
            dialog.setVisible(true);
            while (dialog.okCancelOption == OkCancelOption.UNDECIDED) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (dialog.okCancelOption == OkCancelOption.OK) {
                controller.addExhibition(exhibition);
            }
            dialog.dispose();
        }
        );
    }

    Thread createEditExhibitionDialog (JFrame owner, Exhibition exhibition, ExhibitionPanel panel){
        return new Thread ( ()-> {
            ExhibitionDialog dialog = new ExhibitionDialog(owner, exhibition);
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

    public Exhibition selectExhibitionDialog(JDialog owner, ExhibitionsModel model){
        ExhibitionViewFrame evf = new ExhibitionViewFrame(model, true, true);
        evf.setVisible(true);
        while (evf.okCancelOption == OkCancelOption.UNDECIDED){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (evf.okCancelOption == OkCancelOption.OK){
            evf.dispose();
            return model.getSelectedExhibition();
        } else {
            evf.dispose();
            return null;
        }
    }

    public void createEditExhibitionsListDialog(JDialog owner, ExhibitionsModel exhibitionModel) {
        ExhibitionViewFrame evf = new ExhibitionViewFrame(exhibitionModel, false, false);
        evf.setLocationRelativeTo(owner);
        evf.setVisible(true);
        while (evf.okCancelOption == OkCancelOption.UNDECIDED) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        evf.dispose();
    }
}
