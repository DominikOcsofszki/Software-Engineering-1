package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Price {

    private Price() {}

    public static Double price(ICar car) {
        return priceFactor(car) * car.duration() / Integer.parseInt(Config.SIMULATION_SPEED);
    }

    public static Double priceFactor(ICar car) {
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
        return factors.stream().reduce(0d, (x, y) -> x * y);
    }
}
