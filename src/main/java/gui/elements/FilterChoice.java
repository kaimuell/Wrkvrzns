package gui.elements;

import services.MYToolbarChoice;


import static gui.elements.FilterType.*;

public class FilterChoice extends MYToolbarChoice {

    public FilterChoice(){
        add("Name");
        add("Technik");
        add("Jahr");
        add("Typ");
        this.setSize(40, 30);
    }

    public FilterType getSelectedFilterType(){
        switch (getSelectedItem()){
           case "Name"      : return NAME;
           case "Technik"  : return TECHNIQUE;
           case "Jahr"      : return YEAR;
           case "Typ"       : return TYPE;
           default          : return NAME;
        }
    }
}
