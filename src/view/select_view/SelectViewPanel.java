package view.select_view;

import adressbook.model.ABModel;
import controller.Controller;
import model.Model;
import model.elements.ArtPieceEntry;
import model.ModelViewAccess;
import view.Views;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Panel mit der Standard Darstellung des Werkverzeichnisses
 */
public class SelectViewPanel extends JPanel implements Views {
    private ModelViewAccess model;
    private Controller controller;
    private List<ArtPiecePanel> panelList;

    private static final Color EVEN_ROW_COLOR = new Color(240,240,255);
    private static final Color ODD_ROW_COLOR = new Color(255,255,240);
    private static final Color SELECTED_ELEMENT_COLOR = new Color(255, 218, 220);

    public SelectViewPanel(Controller controller) {
        this.model = new Model(new ABModel());
        this.controller = controller;

    }

    @Override
    public void refreshView() {
        this.removeAll();
        this.panelList= new ArrayList<>(model.getNumberOfEntries());
        JPanel panel = new JPanel(new GridLayout(model.getNumberOfEntries(), 1));

        panel.setBackground(Color.darkGray);
        Iterator<ArtPieceEntry> it = model.artPiecesToView();
        boolean isEvenRowNumber = true;
        while (it.hasNext()) {
            ArtPieceEntry artPiece = it.next();
            Color color = selectColor(artPiece,  isEvenRowNumber);
            ArtPiecePanel artPiecePanel = new ArtPiecePanel (artPiece, controller, color, this);
            panel.add(artPiecePanel);
            panelList.add(artPiecePanel);
            isEvenRowNumber = !isEvenRowNumber;
        }
        this.add(panel);
        revalidate();
        repaint();
    }

    @Override
    public void changeSelectedElements() {
        boolean iseven = true;
        for (ArtPiecePanel panel: panelList) {
            panel.setBackground(selectColor(panel.getArtPiece(), iseven));
            iseven = !iseven;
        }
        repaint();
    }

    @Override
    public void setModelTo(Model model) {
        this.model = model;
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
