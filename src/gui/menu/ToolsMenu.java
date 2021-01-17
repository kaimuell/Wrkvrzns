package gui.menu;

import adressbook.view.AdressBookFrame;
import controller.Controller;

import javax.swing.*;

public class ToolsMenu extends JMenu {
    public ToolsMenu(Controller controller) {
        super("Werkzeuge");
        JMenuItem adressbook = new JMenuItem("Adressbuch öffnen");
        adressbook.addActionListener(action -> {
            AdressBookFrame af =
            new AdressBookFrame(controller.getAddressbook());
            af.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        });
        this.add(adressbook);
    }
}
