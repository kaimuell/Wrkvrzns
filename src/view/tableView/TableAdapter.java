package view.tableView;

import adressbook.model.Person;
import model.Model;
import model.elements.ArtPieceEntry;
import model.elements.ArtworkType;


import javax.swing.table.AbstractTableModel;

public class TableAdapter extends AbstractTableModel {

    private final static String[] COLUMN_NAMES =
            new String[]{"Name", "Typ", "Technik", "Höhe", "Breite", "Tiefe", "Länge", "Jahr", "Preis", "Auflage", "Käufer"};

    private Model model;

    public TableAdapter(Model model) {
        this.model = model;
    }

    @Override
    public String getColumnName(int column){
        return COLUMN_NAMES[column];
    }

    @Override
    public Class getColumnClass(int column){
        return String.class;
    }

    @Override
    public int getRowCount() {
        return model.getNumberOfEntries();
    }


    @Override
    public int getColumnCount() {
        //name, type, technique, height, width, depth, length, year, price, edition, buyer
        return COLUMN_NAMES.length;
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
            case 9 :
                return model.getPieces().get(rowIndex).getEdition();
            case 10:
                Person buyer = model.getPieces().get(rowIndex).getBuyer();
                return  buyer == null ? "nicht verkauft" : buyer.getFirstName()+ ", " + buyer.getFamilyName();
            default: return "";
        }
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String input = (String) aValue;
        ArtPieceEntry entry = model.getPieces().get(rowIndex);
        switch (columnIndex){
            case 0 : entry.setName(input); break;
            case 1 : try{
                ArtworkType type = ArtworkType.valueOf(input.toUpperCase());
                entry.setType(type);
                break;
            } catch (IllegalArgumentException e) {
                break;
            }
            case 2 : entry.setTechnique(input); break;
            case 3 : try{
                int number = Integer.parseInt(input);
                entry.setHeight(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            case 4 : try{
                int number = Integer.parseInt(input);
                entry.setWidth(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            case 5 : try{
                int number = Integer.parseInt(input);
                entry.setDepth(number);
                break;
            }catch (NumberFormatException e){
                break;
            }

            case 6 : try{
                int number = Integer.parseInt(input);
                entry.setLength(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            case 7 : try{
                int number = Integer.parseInt(input);
                entry.setYear(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            case 8 : try{
                int number = Integer.parseInt(input);
                entry.setPrice(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            case 9 : try{
                int number = Integer.parseInt(input);
                entry.setEdition(number);
                break;
            }catch (NumberFormatException e){
                break;
            }
            default: break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 10) return false;
        else return true;
    }
}