package view.tableView;

import adressbook.model.ABModel;
import model.Model;
import view.Viewer;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class TableView extends JTable implements Viewer {
    public TableView() {
        super(new TableAdapter(new Model(new ABModel())));
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
