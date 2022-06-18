package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class Stats {

    private Stats() {
    }

    public static long minCars(IParkingController controller) {
        return controller.getRemovedCars().stream().mapToLong(ICar::price).min().orElse(0L);
    }

    public static long maxCars(IParkingController controller) {
        return controller.getRemovedCars().stream().mapToLong(ICar::price).max().orElse(0L);
    }

}
