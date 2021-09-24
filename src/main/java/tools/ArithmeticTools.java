package tools;

/**
 * Stellt Funktionen zur Berechnung bereit
 */


public class ArithmeticTools {

    /**
     * Rundet die Hunderterstellen einer Ganzzahl
     * @param number die Eingabe Zahl
     * @return die gerundete Zahl
     */
    public static int roundToHundred (int number){
        int cutToTen = number / 10;
        return roundToTen(cutToTen) * 10;
    }

    /**
     * Rundet auf die Zehnerstellen einer Ganzzahl.
     * Ist die letzte Ziffer größer/gleich 5 wird aufgerundet, ansonsten abgerundet.
     * @param number die Eingabezahl
     * @return die gerundete Zahl
     */
    public static int roundToTen (int number){
        int denominator = number % 10;
        int cutNumber = number / 10;
        if (denominator >= 5) {
            return ((cutNumber + 1) * 10);
        }else {
            return cutNumber * 10;
        }
    }

    /**
     * Rundet die Eingabe Zahl auf die nächste Fünfzigerstelle
     * @param number die Eingabe Zahl
     * @return Die gerundete Zahl
     */

    public static int roundToFifty (int number){
        int cutNumber = number / 10;
        int denominator = cutNumber % 10;
        int cutToHundred = cutNumber / 10;
        int newNumber =  cutToHundred * 100;
        if (denominator <= 2){
            return newNumber;
        } else if (denominator <= 6){
            return newNumber + 50;
        } else {
            return newNumber + 100;
        }
    }

    /**
     * Addiert die Prozente zu einer Eingabezahl
     * @param number die Zahl
     * @param percentage die Prozente
     * @return das Ergebnis
     */
    public static float addPercentage(int number, int percentage){
        return number + (number * percentage / 100);
    }


    /**
     * Subtrahiert die Prozente von einer Eingabezahl
     * @param number die Zahl
     * @param percentage die Prozente
     * @return das Ergebnis
     */
    public static float subtractPercentage(int number, int percentage){
        return number - (number * percentage /100);
    }



}
