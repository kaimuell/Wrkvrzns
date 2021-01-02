package view;

import controller.Controller;
import model.ArtPieceEntry;
import model.ModelViewAccess;


import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class ObjectViewPanel extends JPanel implements Views {
    private ModelViewAccess model;
    private Controller controller;

    private static final Color EVEN_ROW_COLOR = new Color(240,240,255);
    private static final Color ODD_ROW_COLOR = new Color(255,255,240);
    private static final Color SELECTED_ELEMENT_COLOR = new Color(255, 218, 220);

    public ObjectViewPanel(ModelViewAccess model, Controller controller) {
        this.model = model;
        this.controller = controller;
        refreshView();
    }

    @Override
    public void refreshView() {
        this.removeAll();
        JPanel panel = new JPanel(new GridLayout(model.getNumberOfEntries(), 1));

        panel.setBackground(Color.lightGray);
        Iterator<ArtPieceEntry> it = model.artPieceIterator();
        boolean isEvenRowNumber = true;
        while (it.hasNext()) {
            ArtPieceEntry artPiece = it.next();
            Color color = selectColor(artPiece,  isEvenRowNumber);
            panel.add(new ArtPiecePanel (artPiece, controller, color));
            isEvenRowNumber = !isEvenRowNumber;
        }
        this.add(panel);
        revalidate();

    }

    private Color selectColor(ArtPieceEntry artPiece, boolean isEvenRowNumber) {
        if (controller.isASelectedElement(artPiece)){
            return SELECTED_ELEMENT_COLOR;
        } else {
            if (isEvenRowNumber){
                return EVEN_ROW_COLOR;
            } else {
                return ODD_ROW_COLOR;
            }
        }
    }
}
