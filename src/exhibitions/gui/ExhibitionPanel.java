package exhibitions.gui;

import exhibitions.controller.ExhibitionsController;
import exhibitions.model.Exhibition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ExhibitionPanel extends JPanel implements MouseListener {
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
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2){
            new ExhibitionDialogController(controller)
                    .createEditExhibitionDialog(owner, exhibition, this)
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
