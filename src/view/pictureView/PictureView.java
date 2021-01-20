package view.pictureView;

import controller.Controller;
import model.Model;
import model.elements.ArtPieceEntry;
import view.Views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@link PictureView} realisiert ein Panel als {@link Views} zur Anzeige der Einträge als Icons ihrer Bitmaps.
 */
public class PictureView extends JPanel implements Views {

    private static final Color STANDARD_BACKGROUND = new Color(144, 143, 143);
    private static final Color SELECTED_ELEMENT_BACKGROUND = new Color(160, 71, 71);
    private int panelWidth;
    private int panelHeight;
    private Model model;
    private Controller controller;
    private List<PictureViewPanel> picturePanels;

    public PictureView(Controller controller, Model model){
        this.panelHeight = 150;
        this.panelWidth = 150;
        this.removeAll();
        this.picturePanels = new ArrayList<>();
        refreshView();
    }

    @Override
    public void refreshView() {
        this.removeAll();
        this.picturePanels = new ArrayList<>();
        for (ArtPieceEntry entry: model.getPieces()) {
            Color color = selectColor(entry);
            PictureViewPanel panel = new PictureViewPanel(controller, panelHeight, panelWidth, entry, color);
            this.add(panel);
            picturePanels.add(panel);
        }
        revalidate();
        repaint();
    }

    private Color selectColor(ArtPieceEntry entry) {
        Color color = controller.isASelectedElement(entry) ? STANDARD_BACKGROUND : SELECTED_ELEMENT_BACKGROUND;
        return color;
    }

    @Override
    public void changeBackgroundOfSelectedElements() {
        for (PictureViewPanel panel : picturePanels) {
            panel.setBackground(selectColor(panel.entry));
        }
    }

    @Override
    public void setModelTo(Model model) {
        this.model = model;
    }
}
