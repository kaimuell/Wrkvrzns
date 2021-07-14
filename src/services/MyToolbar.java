package services;

import javax.swing.*;
import java.awt.*;


/**
 * Die Klasse realisiert eine Toolbar mit den Standard Style Einstellungen des Programms
 */
public class MyToolbar extends JToolBar {
    private static Color TOOLBARCOLOR = new Color(75, 75, 75);

    public MyToolbar(){
        super();
        this.setBackground(TOOLBARCOLOR);
        this.setFloatable(false);
    }
}