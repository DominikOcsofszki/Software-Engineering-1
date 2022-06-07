package parkhouse.util;

import parkhouse.car.ICar;
import parkhouse.config.Config;

import java.util.Date;
import java.util.List;

public class Time {

    private Time() {
    }

    public static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000L;
    public static final long MILLISECONDS_PER_WEEK = MILLISECONDS_PER_DAY * 7;
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
        System.out.println("SIMULATION_START: " + SIMULATION_START);
        System.out.println("getSystemTime(): " + getSystemTime());
        return SIMULATION_START + getMillisecondsSinceStart();
    }

    public static long difference(long time1, long time2) {
        return Math.abs(time1 - time2);
    }

    private static long getSystemTime() {
        return System.nanoTime() / 1000000;
    }
    public static long getTimeFromLastEnteredCar(List<ICar> cars) {
        return cars.get(cars.size() - 1).begin();
    }

    //_do
//    private static long getSystemTime() {
//        long startTime = System.nanoTime();
//        return startTime;
        //
//    }
}