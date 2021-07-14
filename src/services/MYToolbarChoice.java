package services;

import java.awt.*;

/**
 * realisiert ein Auswahlelement mit den Standard Style Einstellungen des Programms
  */
public class MYToolbarChoice extends Choice {
    private static Color BACKGROUNDCOLOR = new Color(90, 90, 90);
    private static Color TEXTCOLOR = new Color(200, 200, 200);

    public MYToolbarChoice() throws HeadlessException {
        super();
        this.setBackground(BACKGROUNDCOLOR);
        this.setForeground(TEXTCOLOR);
    }
}
