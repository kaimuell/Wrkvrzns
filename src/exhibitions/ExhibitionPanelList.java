package exhibitions;

import exhibitions.controller.ExhibitionsController;
import exhibitions.model.Exhibition;
import exhibitions.model.ExhibitionsModel;

import javax.swing.*;
import java.awt.*;

class ExhibitionPanelList extends JPanel implements ExhibitionView{

    private final JFrame owner;
    private final ExhibitionsController controller;
    private final ExhibitionsModel model;
    private static final Color SELECTED_ELEMENT_COLOR = new Color(240,180,180);;
    private static final Color BACKGROUND_COLOR = new Color (220,220,220);

    public ExhibitionPanelList(JFrame owner, ExhibitionsController controller, ExhibitionsModel model) {
        this.owner = owner;
        this.controller = controller;
        this.model = model;

        createPanels();
    }

    private void createPanels() {
        for (Exhibition e : model.getExhibitions()) {
            ExhibitionPanel panel = new ExhibitionPanel(e, controller, owner);
            panel.setBackground(
                    e.equals(model.getSelectedExhibition())? SELECTED_ELEMENT_COLOR : BACKGROUND_COLOR
            );
            this.add(panel);
        }
    }

    @Override
    public void refreshView() {
        this.removeAll();
        createPanels();
    }
}
