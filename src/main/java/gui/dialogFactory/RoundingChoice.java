package gui.dialogFactory;

import languagePack.LanguagePackContainer;
import tools.ArithmeticTools;

import java.awt.*;

public class RoundingChoice extends Choice {

    public RoundingChoice() {
        add(
                LanguagePackContainer.getLanguagePack().getRoundingChoiceDoNotRound()
        );
        add(
                LanguagePackContainer.getLanguagePack().getNumbersHundred() // 100
        );
        add(
                LanguagePackContainer.getLanguagePack().getNumbesFifty()    // 50
        );
        add(
                LanguagePackContainer.getLanguagePack().getNumbersTen()     // 10
        );
    }


    public int roundByChoice (int number){
        switch (this.getSelectedIndex()){
            case 1      :    return ArithmeticTools.roundToHundred(number);
            case 2      :    return ArithmeticTools.roundToFifty(number);
            case 3      :    return ArithmeticTools.roundToTen(number);
            default:    return number;
        }
    }
}
