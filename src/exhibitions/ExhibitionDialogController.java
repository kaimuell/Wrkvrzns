package exhibitions;

import controller.dialogController.OkCancelOption;
import exhibitions.controller.ExhibitionsController;
import exhibitions.model.Exhibition;

import javax.swing.*;

class ExhibitionDialogController {
    ExhibitionsController controller;

    ExhibitionDialogController(ExhibitionsController controller) {
        this.controller = controller;
    }


    Thread createNewExhibitionDialog (JFrame owner){
        return new Thread ( ()-> {
            Exhibition exhibition = new Exhibition();
            ExhibitionDialog dialog = new ExhibitionDialog(owner, exhibition);
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
}
