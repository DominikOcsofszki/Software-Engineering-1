package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class Stats {

    private Stats() {
    }

    public static double minCars(IParkingController controller) {
        return controller.getRemovedCars().stream().mapToDouble(ICar::price).filter(x -> x > 0).min().orElse(0D);
        // .orElseThrow(NoSuchElementException::new);
    }

    public static double maxCars(IParkingController controller) {
        return controller.getRemovedCars().stream().mapToDouble(ICar::price).filter(x -> x > 0).max().orElse(0D);
        // .orElseThrow(NoSuchElementException::new);
    }

}
