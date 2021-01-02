package adressbook.view;

import adressbook.controller.ABController;
import adressbook.model.IABModelReader;
import adressbook.model.PersonEntry;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**Zentrales Panel zum Anzeigen der Einträge im Adressbuch**/


public class ABViewPanel extends JPanel implements ABViewer {
    private final JFrame superFrame;
    private ABController controller;
    private IABModelReader model;
    private static final Color EVEN_ROW_COLOR = new Color(240,240,255);
    private static final Color ODD_ROW_COLOR = new Color(255,255,240);
    private static final Color SELECTED_ELEMENT_COLOR = new Color(255, 218, 220);



    public ABViewPanel(IABModelReader model, ABController controller, JFrame superFrame) {

        this.model = model;
        this.controller = controller;
        this.superFrame = superFrame;
        refreshView();
    }

    public void refreshView(){
        this.removeAll();
        JPanel panel = new JPanel(new GridLayout(model.getNumberOfEntries(), 1));

        panel.setBackground(Color.lightGray);
        Iterator<PersonEntry> it = model.personIterator();
        boolean isEvenRownumber = true;
        while (it.hasNext()) {
            PersonEntry p = it.next();
            Color color = selectColor(p,  isEvenRownumber);
            panel.add(new PersonPanel(p, controller, color, superFrame));
            isEvenRownumber = !isEvenRownumber;
        }
        this.add(panel);
        revalidate();
    }

    private Color selectColor(PersonEntry p, boolean isEvenRownumber) {
        if (p.equals(controller.getSelectedPerson())){
            return SELECTED_ELEMENT_COLOR;
        }else {
            return isEvenRownumber ? EVEN_ROW_COLOR : ODD_ROW_COLOR;
        }
    }

}
