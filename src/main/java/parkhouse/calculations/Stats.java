package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public class Stats {

    private Stats() {}

    public static long minCars(List<ICar> cars) {
        return cars.stream().mapToLong(ICar::price).min().orElse(0L);
    }

    public static long maxCars(List<ICar> cars) {
        return cars.stream().mapToLong(ICar::price).max().orElse(0L);
    }

    public static long sumCars(List<ICar> cars) {
        return cars.stream().mapToLong(ICar::price).sum();
    }

    public static long avgCars(List<ICar> cars) {
        int count = cars.size() == 0 ? 1 : cars.size();
        return sumCars(cars) / count;
    }

}
