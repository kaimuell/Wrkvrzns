package services;

import javax.swing.*;
import java.awt.*;

/**
 * realisiert ein Auswahlelement mit den Standard Style Einstellungen des Programms
  */
public class MYToolbarSelectionBox extends JComboBox {
    private static Color BACKGROUNDCOLOR = new Color(90, 90, 90);
    private static Color TEXTCOLOR = new Color(200, 200, 200);

    public MYToolbarSelectionBox(String[] options) throws HeadlessException {
        super(options);
        this.setBackground(BACKGROUNDCOLOR);
        this.setForeground(TEXTCOLOR);
    }
}
