package parkhouse.util;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.List;

public class Time {

    private Time() {
    }

    public static final long INSTANCE_START_DATE = System.currentTimeMillis();

    public static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000L;
    public static final long MILLISECONDS_PER_WEEK = MILLISECONDS_PER_DAY * 7;

    public static long diff(long a, long b) {
        return Math.abs(a - b);
    }

    public static long getTimeFromLastEnteredCar(List<ICar> cars) {
        int lastItem = cars.size() != 0 ? cars.size() - 1 : 0;
        return cars.get(lastItem).begin();
    }

    public static long getTimeFromLastEnteredCarCheckBoth(List<ICar> cars, List<ICar> carsRem) {
        if (cars.size() != 0) {
            int lastItem = cars.size() -1;
            return cars.get(lastItem).begin();
        } else {
            int lastItem = carsRem.size() == 0 ? 0 : carsRem.size() - 1;
            return carsRem.get(lastItem).begin();
        }
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static long simTime(long realTime) {
        return INSTANCE_START_DATE + (Config.SIMULATION_SPEED * (realTime - INSTANCE_START_DATE));
    }

    public static long realTime(long simTime) {
        return (simTime - INSTANCE_START_DATE) / Config.SIMULATION_SPEED + INSTANCE_START_DATE;
    }

    public static long simNow() {
        return simTime(System.currentTimeMillis());
    }
}