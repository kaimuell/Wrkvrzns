package gui.menu;

import view.ViewHub;
import view.ViewOption;

import javax.swing.*;

class ViewsMenu extends JMenu{
    ViewHub viewHub;

    ViewsMenu(ViewHub viewHub){
        super("Ansicht");
        this.viewHub = viewHub;

        JMenuItem setSelectionView = new JMenuItem("Auswahl-Ansicht");
        setSelectionView.setToolTipText("Die Standard Ansicht des Programms");
        setSelectionView.addActionListener( action ->{
            viewHub.setViewModeTo(ViewOption.SELECT_VIEW);
        });
        this.add(setSelectionView);

        JMenuItem setPictureView = new JMenuItem("Bilder-Ansicht");
        setPictureView.setToolTipText("Ansicht aus Kacheln von Bildern");
        setPictureView.addActionListener(action -> {
            viewHub.setViewModeTo(ViewOption.PICTURE_VIEW);
        });
        this.add(setPictureView);

        JMenuItem setTableView = new JMenuItem("Tabellen-Ansicht");
        setTableView.setToolTipText("Ansicht in Tabellenform");
        setTableView.addActionListener(actoin -> {
            viewHub.setViewModeTo(ViewOption.TABLE_VIEW);
        });
        this.add(setTableView);
    }
}
