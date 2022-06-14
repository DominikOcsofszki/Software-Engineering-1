package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Price {

    private Price() {}

    public static String format(long price) {
        return String.format(Locale.US, "%.2f€", price / 100d);
    }

    public static long out(long price) {
        return price / 100;
    }

    public static long priceFactDurationSimSpeed(ICar car) {   //Changed name since always confusing what price() is.
        return Math.round(priceFactor(car) * car.duration() / Config.SIMULATION_SPEED);
    }

    public static double priceFactor(ICar car) {
        List<Double> factors = new ArrayList<>();
        List<String> applied = new ArrayList<>();
        for (String PF : Config.PRICE_FACTOR) {
            if (PF.matches("[A-Za-z]+\\.[A-Za-z]+:[0-9.]+")) {
                String[] f = PF.split(":");
                Double factor = Double.parseDouble(f[1]);
                String[] types = f[0].split("\\.", 1);
                if (types[0].equals(car.category()) && types[1].equals(car.type())) {
                    factors.add(factor);
                    applied.add(types[0]);
                }
            }
        }
        for (String PF : Config.PRICE_FACTOR) {
            if (PF.matches("[A-Za-z]+:[0-9.]+")) {
                String[] f = PF.split(":");
                Double factor = Double.parseDouble(f[1]);
                if ((f[0].equals(car.category()) || f[0].equals(car.type())) && !applied.contains(f[0])) {
                    factors.add(factor);
                }
            }
        }
        return factors.stream().reduce(1d, (x, y) -> x * y);
    }
}
