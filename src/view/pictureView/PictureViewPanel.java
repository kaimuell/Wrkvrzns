package view.pictureView;

import controller.Controller;
import model.elements.ArtPieceEntry;
import view.PanelMouseInputScheme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
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
    private Color color;

    PictureViewPanel(Controller controller, int panelHeight, int panelWidth, ArtPieceEntry entry, Color color ) {
        this.height = panelHeight;
        this.width = panelWidth;
        this.bitmap = entry.getBitmap();
        this.entry = entry;
        this.color = color;
        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setBorder(new BasicBorders.MarginBorder());
        this.setBackground(color);
        this.addMouseListener(new PanelMouseInputScheme(controller, entry));
        createFittingLabel();
        revalidate();
    }

    private void createFittingLabel(){
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(
                bitmap.getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        imageLabel.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(color);
                g.drawRect(x, y, width, height);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(3,3,3,3);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
        this.add(imageLabel, BorderLayout.CENTER);
    }
}

