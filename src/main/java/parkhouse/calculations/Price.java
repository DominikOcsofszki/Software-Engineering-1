package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Price {

    private Price() {}

    public static String format(long price) {
        return String.format(Locale.US, "%.2f€", price / 100d);
    }

    /*
    public static long out(long price) {
        return price / 100;
    }
     */

    /*
    public static long priceFactDurationSimSpeed(ICar car) {   //Changed name since always confusing what price() is.
        return Math.round(priceFactor(car) * car.duration() / Config.SIMULATION_SPEED);
    }
     */

    public static double priceFactor(ICar car) {
        List<String> price_factors = new ArrayList<>(Arrays.asList(Config.PRICE_FACTOR));
        for (String PF : price_factors) {
            if (PF.matches("[A-Za-z]+\\.[A-Za-z]+:[0-9.]+")) {
                String[] f = PF.split(":");
                String[] types = f[0].split("\\.", 1);
                if (types[0].equals(car.category()) && types[1].equals(car.type())) {
                    return Double.parseDouble(f[1]);
                }
                price_factors.remove(PF);
            }
        }
        for (String PF : Config.PRICE_FACTOR) {
            String[] f = PF.split(":");
            if ((f[0].equals(car.category()))) {
                return Double.parseDouble(f[1]);
            }
            price_factors.remove(PF);
        }
        for (String PF : Config.PRICE_FACTOR) {
            String[] f = PF.split(":");
            if ((f[0].equals(car.type()))) {
                return Double.parseDouble(f[1]);
            }
        }
        return 1d;
    }
}
