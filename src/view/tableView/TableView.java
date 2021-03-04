package view.tableView;

import adressbook.model.ABModel;
import exhibitions.ExhibitionsModel;
import model.Model;
import view.Viewer;

import javax.swing.*;

/**
 * Die Klasse {@link TableView} realisiert eine Tabellen-Ansicht des Models.
 */

public class TableView extends JTable implements Viewer {
    public TableView() {
        super(new TableAdapter(new Model(new ABModel(), new ExhibitionsModel(null))));
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
