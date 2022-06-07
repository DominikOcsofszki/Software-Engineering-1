package parkhouse.calculations;

import parkhouse.config.Config;

import java.util.List;

public class Calc {             //Class for Calculations

    private Calc() {
    }           //private so can not be initiated


    public static double calcInCent(double x) {
        return x / Config.FACTOR_PRICE_VIEW;
    }
}
