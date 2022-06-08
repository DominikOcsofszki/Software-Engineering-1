package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public abstract class AbstractStats {

    public double template1(IParkingController controller) {
        double sum = controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
        sum = optional(controller, sum);
        return sum;
    }

    abstract double optional(IParkingController controller, double sum);

}
