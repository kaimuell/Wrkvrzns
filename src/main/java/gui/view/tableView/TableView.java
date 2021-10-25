package gui.view.tableView;

import adressbook.model.ABModel;
import exhibitions.model.ExhibitionsModel;
import model.Model;
import gui.view.Viewer;
import model.ModelContainer;

import javax.swing.*;

/**
 * Die Klasse {@link TableView} realisiert eine Tabellen-Ansicht des Models.
 */

public class TableView extends JTable implements Viewer {
    public TableView() {
        super(new TableAdapter(ModelContainer.getModel()));
    }

    @Override
    public void refreshView() {
        super.revalidate();
    }

    @Override
    public void changeBackgroundOfSelectedElements() {

    }

    @Override
    public void setModelTo(Model model) {
        super.dataModel = new TableAdapter(model);
    }
}
