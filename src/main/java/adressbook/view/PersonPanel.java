package adressbook.view;


import adressbook.controller.ABController;
import adressbook.model.PersonEntry;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Panel zur verkürzeten Anzeige einer Person. Bei Klick darauf wird der PersonEntry selektiert, bei Dopppelklick der der Eintrag verändert.
 */
public class PersonPanel extends JPanel implements MouseInputListener {
    private Color rowColor;
    private PersonEntry person;
    private ABController controller;
    private JFrame superFrame;

    public PersonPanel(PersonEntry person, ABController controller, Color rowColor, JFrame superFrame) {

        this.person = person;
        this.controller = controller;
        this.rowColor = rowColor;
        this.superFrame = superFrame;
        this.setPreferredSize(new Dimension(500, 30));
        this.setBackground(rowColor);
        this.addMouseListener(this);
        refreshView();

    }

    private void refreshView() {
        setLayout(new GridLayout(0 , 3, 0, 0));
        JLabel firstNameLabel = new JLabel(person.getFirstName());
        firstNameLabel.setHorizontalTextPosition(Label.LEFT);
        firstNameLabel.setSize(150, 30);
        firstNameLabel.setBackground(rowColor);
        add(firstNameLabel);
        JLabel lastNameLabel = new JLabel( person.getFamilyName());
        lastNameLabel.setBackground(rowColor);
        lastNameLabel.setSize(150, 30);
        add(lastNameLabel);
        JLabel eMailLabel = new JLabel(person.geteMail());
        eMailLabel.setSize(200, 30);
        eMailLabel.setBackground(rowColor);
        add(eMailLabel);
        revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.selectPerson(person);
        if (e.getClickCount() == 2){
            new DialogHandler(controller).createModifyPersonDialogThread(superFrame,person).start();
        }
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
