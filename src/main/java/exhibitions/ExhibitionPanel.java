package exhibitions;

import exhibitions.entities.Exhibition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Panel zur Anzeige einer Ausstellung in der {@link ExhibitionPanelList}
 */
class ExhibitionPanel extends JPanel implements MouseListener {
    private final Exhibition exhibition;
    private final ExhibitionsController controller;
    private final JFrame owner;
    private final boolean isEditable;

    ExhibitionPanel(Exhibition exhibition, ExhibitionsController controller, JFrame owner, boolean isEditable) {
        this.exhibition = exhibition;
        this.controller = controller;
        this.owner = owner;
        this.isEditable = isEditable;

        this.setLayout(new GridLayout(1, 4));
        this.add(new JLabel(String.valueOf(exhibition.getYear())));
        this.add(new JLabel(exhibition.getName()));
        this.add(new JLabel(exhibition.getPlace()));
        this.add(new JLabel(exhibition.getCity()));
        this.add(new JLabel(exhibition.getType().toString()));
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2){
            new ExhibitionViewManager(controller)
                    .createEditExhibitionDialog(owner, exhibition, this, isEditable)
                    .start();
        }
        controller.selectExhibition(exhibition);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
