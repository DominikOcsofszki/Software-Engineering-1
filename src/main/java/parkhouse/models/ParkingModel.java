package parkhouse.models;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParkingModel implements IParkingModel {

    private final List<ICar> cars;
    private final List<IObserver> observers;
    private final List<ICar> removedCars;
    /*
    Sim Speed test
    private long[] systemzeit = new long[2];
    private long[] autoenter = new long[2];
    int countersystem = 0;
    int counterauto = 0;
     */

    public ParkingModel() {
        cars = new ArrayList<>();
        observers = new ArrayList<>();
        removedCars = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update();
        }
    }

    @Override
    public void addCar(String[] params) {
        cars.add(new Car(params));
        notifyObservers();
        /*
        Sim speed test
        systemzeit[countersystem++] = System.nanoTime() / 1000000;
        autoenter[counterauto++] = Long.parseLong(params[1]);
        if(counterauto == 2) {
            System.out.println("diff simulation: " + Math.abs(autoenter[0] - autoenter[1]));
            System.out.println("diff system: " + Math.abs(systemzeit[0] - systemzeit[1]));
            counterauto = 0;
            countersystem = 0;
        }
         */

    }

    @Override
    public void removeCar(String[] params) {
        // remove double code? _do
        /*Car tmp = new Car(params);
        removedCars.add(tmp);
        cars.removeIf(c -> c.license().equals(tmp.license()));*/
        //----
        removedCars.add(new Car(params));
        cars.removeIf(c -> c.license().equals(new Car(params).license()));
        notifyObservers();
    }

    @Override
    public Double dailyEarnings() {
        double sum = 0D;
        // functional way _do
        /*sum = removedCars.stream()
                .filter(x -> (Instant.now().getEpochSecond() - x.end() < MILLISECONDS_PER_DAY))
                .mapToDouble(ICar::price)
                .sum();*/
        //return sum;
        for(ICar car : removedCars) {
            if(Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_DAY) sum += car.price();
        }
        return sum;
    }

    @Override
    public Double weeklyEarnings() {
        double sum = 0D;
        for(ICar car : removedCars) {
            if(Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_WEEK) sum += car.price();
            else removedCars.remove(car);
        }
        return sum;
    }

    @Override
    public List<String> currentCost() {
        List<String> cost = new ArrayList<>();
        long now = Time.now();
        for (ICar c : cars) {
            cost.add(String.valueOf(((now - c.begin()) / 60000d) * Config.PRICE));
        }
        return cost;
    }

    @Override
    public List<String> licensePlates() {
        List<String> licenses = new ArrayList<>();
        for (ICar c : cars) {
            licenses.add(c.license());
        }
        return licenses;
    }
}
