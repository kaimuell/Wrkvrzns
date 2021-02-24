package exhibitions;

import exhibitions.controller.ExhibitionsController;
import exhibitions.model.Exhibition;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

class ExhibitionPanel extends JPanel implements MouseInputListener {
    private final Exhibition exhibition;
    private final ExhibitionsController controller;
    private final JFrame owner;

    ExhibitionPanel(Exhibition exhibition, ExhibitionsController controller, JFrame owner) {
        this.exhibition = exhibition;
        this.controller = controller;
        this.owner = owner;

        this.setLayout(new GridLayout(1, 4));
        this.add(new JLabel(String.valueOf(exhibition.getYear())));
        this.add(new JLabel(exhibition.getName()));
        this.add(new JLabel(exhibition.getPlace()));
        this.add(new JLabel(exhibition.getCity()));
        this.add(new JLabel(exhibition.getType().toString()));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            new ExhibitionDialogController(controller)
                    .createEditExhibitionDialog(owner, exhibition, this)
                    .start();
        } else controller.selectExhibition(exhibition);
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
