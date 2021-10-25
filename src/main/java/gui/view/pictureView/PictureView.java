package gui.view.pictureView;

import adressbook.model.ABModel;
import controller.Controller;
import exhibitions.model.ExhibitionsModel;
import model.Model;
import model.ModelContainer;
import model.ModelViewAccess;
import model.elements.ArtPieceEntry;
import gui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Die Klasse {@link PictureView} realisiert ein Panel als {@link Viewer} zur Anzeige der Einträge als Icons ihrer Bitmaps.
 */
public class PictureView extends JPanel implements Viewer {

    private static final Color STANDARD_BACKGROUND = new Color(144, 143, 143);
    private static final Color SELECTED_ELEMENT_BACKGROUND = new Color(160, 71, 71);
    private int panelWidth;
    private int panelHeight;
    private ModelViewAccess model;
    private final Controller controller;
    private List<PictureViewPanel> picturePanels;

    public PictureView(Controller controller){
        this.panelHeight = 200;
        this.panelWidth = 200;
        this.model = ModelContainer.getModel();
        this.controller = controller;
        this.picturePanels = new ArrayList<>();
        this.setLayout(new GridLayout(0,8));
        this.setBackground(STANDARD_BACKGROUND);
        refreshView();
    }

    @Override
    public void refreshView() {
        this.removeAll();
        this.picturePanels = new ArrayList<>();
        Iterator<ArtPieceEntry> it = model.artPiecesToView();
        while (it.hasNext()) {
            ArtPieceEntry entry = it.next();
            Color color = selectColor(entry);
            PictureViewPanel panel = new PictureViewPanel(controller, panelHeight, panelWidth, entry, color);
            this.add(panel);
            picturePanels.add(panel);
        }
        revalidate();
        repaint();
    }

    private Color selectColor(ArtPieceEntry entry) {
        return controller.isASelectedElement(entry) ? SELECTED_ELEMENT_BACKGROUND : STANDARD_BACKGROUND;
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
