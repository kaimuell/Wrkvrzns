package exhibitions;

import exhibitions.model.ExhibitionsModel;
import gui.dialogFactory.OkCancelOption;

public class VoidWindowController implements ExhibitionWindowController{
    public VoidWindowController() {
    }

    @Override
    public void release(ExhibitionViewFrame evf, ExhibitionsModel model, OkCancelOption okCancelOption) {
        evf.dispose();
    }
}
