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
        return sumCars(controller) / count; // Hier unsicher ob sumCars verwendet werden sollte,
        // da sich sum verändern könnte, während count zuvor
        //nicht ganz sicher. Sollte copy erstellt werden?
    }

    public static double minCars(IParkingController controller) {
//        double ret = getRemovedCarsController().stream().mapToDouble(ICar::price).filter(x -> x > 0).min().orElseThrow(NoSuchElementException::new);
        return controller.getRemovedCars().stream().mapToDouble(ICar::price).filter(x -> x > 0).min().orElse(0D);
    }

    public static double maxCars(IParkingController controller) {
//        double ret = getRemovedCarsController().stream().mapToDouble(ICar::price).filter(x -> x > 0).max().orElseThrow(NoSuchElementException::new); //return the max price
        return controller.getRemovedCars().stream().mapToDouble(ICar::price).filter(x -> x > 0).max().orElse(0D);
    }

}
