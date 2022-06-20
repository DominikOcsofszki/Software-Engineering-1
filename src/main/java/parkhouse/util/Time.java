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

    /*
    public static final long SIMULATION_START = getSystemTime();


    public static long getMillisecondsSinceStart() {
        return (getSystemTime() - SIMULATION_START) * 10;
    }

    public static long getSecondsSinceStart() {
        return getMillisecondsSinceStart() / 1000;
    }

    public static long getMinutesSinceStart() {
        return getSecondsSinceStart() / 60;
    }

    public static long getHoursSinceStart() {
        return getMinutesSinceStart() / 60;
    }

    public static long getDaysSinceStart() {
        return getHoursSinceStart() / 24;
    }

    public static String getTime() {
        return getDaysSinceStart() + ":" + getHoursSinceStart() % 24 + ":" + getMinutesSinceStart() % 60 + ":"
                + getSecondsSinceStart() % 60 + ":" + getMillisecondsSinceStart() % 1000;
    }

    public static long now() {
        return SIMULATION_START + getMillisecondsSinceStart();
    }

    private static long getSystemTime() {
        return System.nanoTime() / 1000000;
    }
    */

    public static long difference(long time1, long time2) {
        return Math.abs(time1 - time2);
    }

    public static long getTimeFromLastEnteredCar(List<ICar> cars) {
        int lastItem = cars.size() != 0 ? cars.size() - 1 : 0;
        return cars.get(lastItem).begin();
    }

    public static long getTimeFromLastEnteredCarCheckBoth(List<ICar> cars, List<ICar> carsRem) {
//        if(cars == null | carsRem == null | cars.size() == 0 | carsRem.size() == 0) //ToDo needed?
        if (cars.size() != 0) {
            int lastItem = cars.size() -1;
            return cars.get(lastItem).begin();
        } else {
            int lastItem = carsRem.size() == 0 ? 0 : carsRem.size() - 1;
            return carsRem.get(lastItem).begin();   //ToDo how test
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