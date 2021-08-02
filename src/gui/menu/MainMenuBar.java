package gui.menu;

import controller.Controller;
import view.ViewHub;

import javax.swing.*;
import javax.swing.text.View;

/**
 * Die MenuBar des Programms
 */
public class MainMenuBar extends JMenuBar {
    public MainMenuBar(JFrame parentFrame, Controller controller, ViewHub viewHub) {
        super();
        this.add(new DataMenu(parentFrame, controller));
        this.add(new ToolsMenu(parentFrame, controller));
        this.add(new ViewsMenu(viewHub));
        this.add(new OutputMenue(controller, parentFrame));
        this.setVisible(true);
    }
}
