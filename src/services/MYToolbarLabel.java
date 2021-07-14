package services;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse realisiert ein Label für Toolbars mit den Standard Style Einstellungen des Programms
 */
public class MYToolbarLabel extends JLabel {
    private static Color TEXTCOLOR = new Color(190, 190, 190);

    public MYToolbarLabel(String text) {
        super(text);
        this.setForeground(TEXTCOLOR);
    }
}
