package gui.elements;

import java.awt.*;

/**
 * Implementiert eine Auswahlbox um eine Art der Sortierung auszuwählen
 */
public class SorterChoice extends Choice {
    public SorterChoice(){
        add("Name <");
        add("Name >");
        add("Typ <");
        add("Typ >");
        add("Jahr <");
        add("Jahr >");
        add("Preis <");
        add("Preis >");
    }

    public SortingType getSortingType(){
        switch (getSelectedItem()){
            case "Name >" : return SortingType.NAME_BIGGER;
            case "Name <" : return SortingType.NAME_SMALLER;
            case "Typ >"  : return SortingType.TYPE_BIGGER;
            case "Typ <"  : return SortingType.TYPE_SMALLER;
            case "Jahr >" : return SortingType.YEAR_BIGGER;
            case "Jahr <" : return SortingType.YEAR_SMALLER;
            case "Preis >"  : return SortingType.PRICE_BIGGER;
            case "Preis <"  : return SortingType.PRICE_SMALLER;
            default: return SortingType.NAME_SMALLER;
        }
    }
}
