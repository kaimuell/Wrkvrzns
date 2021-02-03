package view.tableView;

import adressbook.model.Person;
import model.Model;


import javax.swing.table.AbstractTableModel;

public class TableAdapter extends AbstractTableModel {


    private Model model;

    public TableAdapter(Model model) {
        this.model = model;
    }

    @Override
    public int getRowCount() {
        return model.getNumberOfEntries();
    }

    @Override
    public int getColumnCount() {
        //name, type, technique, height, width, depth, length, year, price, buyer
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return model.getPieces().get(rowIndex).getName();
            case 1:
                return model.getPieces().get(rowIndex).getType().toString();
            case 2:
                return model.getPieces().get(rowIndex).getTechnique();
            case 3:
                return model.getPieces().get(rowIndex).getHeight();
            case 4:
                return model.getPieces().get(rowIndex).getWidth();
            case 5:
                return model.getPieces().get(rowIndex).getDepth();
            case 6:
                return model.getPieces().get(rowIndex).getLength();
            case 7:
                return model.getPieces().get(rowIndex).getYear();
            case 8:
                return model.getPieces().get(rowIndex).getPrice();
            case 9:
                Person buyer = model.getPieces().get(rowIndex).getBuyer();
                return buyer.getFirstName() + buyer.getFamilyName();
        }
        return null;
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //TODO

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 9) return false;
        else return true;
    }
}