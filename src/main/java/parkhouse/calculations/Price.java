package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Price {

    private Price() {}

    public static double out(double price) {
        return price / 1000d;
    }

    public static double price(ICar car) {
        return priceFactor(car) * car.duration() / Integer.parseInt(Config.SIMULATION_SPEED);
    }

    public static double price(ICar car, long now) {
        return priceFactor(car) * (now - car.begin()) / Integer.parseInt(Config.SIMULATION_SPEED);
    }

    public static double priceFactor(ICar car) {
        List<Double> factors = new ArrayList<>();
        for (String PF : Config.PRICE_FACTOR) {
            String[] f = PF.split(":");
            Double factor = Double.parseDouble(f[1]);
            String[] types = f[0].split("\\.");
            boolean match = true;
            for (String t : types) {
                if (!t.equals(car.type()) && !t.equals(car.category())) {
                    match = false;
                    break;
                }
            }
            if (match) {
                factors.add(factor);
            }
        }
        return factors.stream().reduce(1d, (x, y) -> x * y);
    }
}
