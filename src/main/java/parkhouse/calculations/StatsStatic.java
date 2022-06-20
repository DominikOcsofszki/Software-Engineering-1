package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public class StatsStatic {

    public static long sumAllCars(IParkingController controller) {
        return sum(controller.getAllCars());
    }

    public static long sumInHouseCars(IParkingController controller) {
        return sum(controller.getCars());
    }

    public static long sumRemovedCars(IParkingController controller) {
        return sum(controller.getRemovedCars());
    }

    public static long avgAllCars(IParkingController controller) {
        return avg(controller.getAllCars());
    }

    public static long avgInHouseCars(IParkingController controller) {
        return avg(controller.getCars());
    }

    public static long avgRemovedCars(IParkingController controller) {
        return avg(controller.getRemovedCars());
    }

    private static long sum(List<ICar> carList) {
        return carList.stream()
                .map(ICar::price)
                .reduce(0L, Long::sum);
    }

    private static long avg(List<ICar> carList) {
        return sum(carList) / carList.size();
    }
}