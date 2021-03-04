package exhibitions;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


/**
 * Panel, dass alle Ausstellungen zusammenfasst.
 */
class ExhibitionPanelList extends JPanel implements ExhibitionView{

    private final JFrame owner;
    private final ExhibitionsController controller;
    private final ExhibitionsModel model;
    private static final Color SELECTED_ELEMENT_COLOR = new Color(240,180,180);;
    private static final Color BACKGROUND_COLOR = new Color (220,220,220);
    private final boolean isEditable;

    ExhibitionPanelList(JFrame owner, ExhibitionsController controller, ExhibitionsModel model, boolean isEditable) {
        this.owner = owner;
        this.controller = controller;
        this.model = model;
        this.isEditable = isEditable;
        createPanels();
    }

    private void createPanels() {
        JPanel mainPanel = new JPanel(new GridLayout(model.getExhibitions().size(), 1));
        Iterator<Exhibition> iterator = model.getExhibitonIterator();
        while (iterator.hasNext()){
            Exhibition e = iterator.next();
            ExhibitionPanel panel = new ExhibitionPanel(e, controller, owner, isEditable);
            panel.setBackground(
                    e.equals(model.getSelectedExhibition())? SELECTED_ELEMENT_COLOR : BACKGROUND_COLOR
            );
            mainPanel.add(panel);
        }
        this.add(mainPanel);
        this.revalidate();
    }

    @Override
    public void refreshView() {
        this.removeAll();
        createPanels();
    }
}
