package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class Stats {

    private Stats() {
    }

    public static double sumCars(IParkingController controller) {
        return controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
    }

    public static double avgCars(IParkingController controller) {
        long count = controller.getRemovedCars().stream().filter(x -> (x.price() > 0)).count();
        if (count == 0) return 0; //ToDo count != sumCars().count? Da unterschiedlich zur Berechnung?
        return sumCars(controller) / count;
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
