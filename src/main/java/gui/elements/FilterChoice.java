package gui.elements;

import languagePack.LanguagePackContainer;
import services.MYToolbarSelectionBox;


import static gui.elements.FilterType.*;

/**
 * Implemenitert eine Auswahlbox um die Art des Filters Auszuwählen
 **/
public class FilterChoice extends MYToolbarSelectionBox {

    public FilterChoice(){
        super(LanguagePackContainer.getLanguagePack().getFilterChoiceOtions());
        this.setSize(40, 30);
    }

    public FilterType getSelectedFilterType(){
        switch (getSelectedIndex()){
           case 0           : return NAME;
           case 1           : return TECHNIQUE;
           case 2           : return YEAR;
           case 3           : return TYPE;
           default          : return NAME;
        }
    }
}
