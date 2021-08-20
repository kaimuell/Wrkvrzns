package controller.dialogFactory;

import tools.ArithmeticTools;

import java.awt.*;

public class RoundingChoice extends Choice {

    public RoundingChoice() {
        add("Nicht Runden");
        add("Hundert");
        add("Fünfzig");
        add("Zehn");
    }


    public int roundByChoice (int number){
        switch (this.getSelectedItem()){
            case "Hundert" :    return ArithmeticTools.roundToHundred(number);
            case "Fünfzig" :    return ArithmeticTools.roundToFifty(number);
            case "Zehn"    :    return ArithmeticTools.roundToTen(number);
            default:    return number;
        }
    }
}
