package gui.elements;

import languagePack.LanguagePackContainer;
import services.MYToolbarSelectionBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Implementiert eine Auswahlbox um eine Art der Sortierung auszuwählen
 */
public class SorterChoice extends MYToolbarSelectionBox {
    public SorterChoice(){
        super(LanguagePackContainer.getLanguagePack().getSorterChoiceOptions()
        );


    }

    public SortingType getSortingType(){
        switch (getSelectedIndex()){
            case 0 : return SortingType.NAME_SMALLER;
            case 1 : return SortingType.NAME_BIGGER;
            case 2  : return SortingType.TYPE_SMALLER;
            case 3  : return SortingType.TYPE_BIGGER;
            case 4 : return SortingType.YEAR_SMALLER;
            case 5 : return SortingType.YEAR_BIGGER;
            case 6  : return SortingType.PRICE_SMALLER;
            case 7  : return SortingType.PRICE_BIGGER;
            default: return SortingType.NAME_SMALLER;
        }
    }
}
