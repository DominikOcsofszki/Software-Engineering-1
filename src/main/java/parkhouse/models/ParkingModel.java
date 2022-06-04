package parkhouse.models;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.views.IObserver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParkingModel implements IParkingModel {

    private final List<ICar> cars;
    private final List<IObserver> observers;

    public ParkingModel() {
        this.cars = new ArrayList<>();
        this.observers = new ArrayList<>();
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
        ICar car = new Car(params);
        cars.add(car);
        notifyObservers();
    }

    @Override
    public void removeCar(String[] params) {
        ICar car = new Car(params);
        cars.removeIf(c -> c.license().equals(car.license()));
        notifyObservers();
    }

    @Override
    public Double dailyEarnings() {
        return 0.0;
    }

    @Override
    public Double weeklyEarnings() {
        return 0.0;
    }

    @Override
    public List<String> currentCost() {
        List<String> cost = new ArrayList<>();
        long now = Instant.now().getEpochSecond() * 1000; // TODO Get the correct sim time
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
