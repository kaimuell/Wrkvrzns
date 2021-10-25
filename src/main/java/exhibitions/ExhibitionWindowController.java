package exhibitions;

import exhibitions.ExhibitionViewFrame;
import exhibitions.model.ExhibitionsModel;
import gui.dialogFactory.OkCancelOption;

public interface ExhibitionWindowController {
    void release(ExhibitionViewFrame evf, ExhibitionsModel model, OkCancelOption okCancelOption);
}
