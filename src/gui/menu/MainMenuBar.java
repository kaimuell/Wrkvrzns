package gui.menu;

import controller.Controller;
import view.ViewHub;

import javax.swing.*;
import javax.swing.text.View;

public class MainMenuBar extends JMenuBar {
    public MainMenuBar(Controller controller, ViewHub viewHub) {
        super();
        this.add(new DataMenu(controller));
        this.add(new ToolsMenu(controller));
        this.add(new ViewsMenu(viewHub));
        this.setVisible(true);
    }
}
