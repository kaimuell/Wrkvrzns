package view.pictureView;

import controller.Controller;
import model.elements.ArtPieceEntry;
import view.PanelMouseInputScheme;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse {@link PictureViewPanel} realisiert ein einzelnes Panel im {@link PictureView},
 * in der ein {@link ArtPieceEntry} über sein Bitmap dargestellt wird. Es ist ein {@link PanelMouseInputScheme}
 * als Listener angemeldet, welches Operationen über den {@link Controller} ausführt.
 */

class PictureViewPanel extends JPanel {
    private final int height;
    private final int width;
    private final Image bitmap;
    public final ArtPieceEntry entry;

    PictureViewPanel(Controller controller, int panelHeight, int panelWidth, ArtPieceEntry entry, Color color ) {
        this.height = panelHeight;
        this.width = panelWidth;
        this.bitmap = entry.getBitmap();
        this.entry = entry;
        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setBackground(color);
        this.addMouseListener(new PanelMouseInputScheme(controller, entry));
        createFittingLabel();
        revalidate();
    }

    private void createFittingLabel(){
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(
                bitmap.getScaledInstance(((int)(width*0.8)), ((int)(height*0.8)), Image.SCALE_DEFAULT)));
        this.add(imageLabel, BorderLayout.CENTER);
    }

}

