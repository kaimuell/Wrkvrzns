package exhibitions;

import exhibitions.gui.ExhibitionViewFrame;
import exhibitions.model.ExhibitionsModel;

public class TestExhibitions {
    public static void main(String[] args){
        ExhibitionViewFrame evf = new ExhibitionViewFrame(new ExhibitionsModel(null), true, true);
        evf.setVisible(true);
    }
}
