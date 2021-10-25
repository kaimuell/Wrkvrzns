package tools;


import static tools.ArithmeticTools.*;

public class ArithmeticToolsTests {


    void testArithmeticTools(){
        assert (roundToTen(15) == 20);
        assert (roundToTen(12) == 10);
        assert (roundToHundred(162) == 200);
        assert (roundToHundred(222) == 200);
        assert (roundToHundred(0) == 0);
        assert (roundToFifty(1526) == 1500);
        assert (roundToFifty(1042) == 1050);
        assert (roundToFifty(1182) == 1200);
        assert (addPercentage(100, 10) == 110.0);
        assert (subtractPercentage(100, 20) == 80);
    }
}
