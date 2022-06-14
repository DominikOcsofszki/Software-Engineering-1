package parkhouse.models;

import parkhouse.calculations.Price;
import parkhouse.car.ICar;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingModel implements IParkingModel {

    private final List<ICar> cars;
    private final List<IObserver> observers;
    private final List<ICar> removedCars;

    public ParkingModel() {
        cars = new ArrayList<>();       //cars still in the Parkhouse
        observers = new ArrayList<>();
        removedCars = new ArrayList<>();    //cars that have been in the Parkhouse -> paid
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
    public void addCar(ICar car) {
        cars.add(car);
        notifyObservers();
    }

    @Override
    public void removeCar(ICar car) {
        removedCars.add(car);
        cars.remove(car);
        notifyObservers();
    }

    //-----

    @Override
    public void addCarRestartServer(ICar car) {
        cars.add(car);
        notifyObservers();    //ToDo notify when all all cars entered for performance
    }

    @Override
    public void removeCarRestartServer(ICar car) {
        removedCars.add(car);
        notifyObservers();  //ToDo notify when all all cars entered for performance
    }
    //--

    private long earningHelper(long timeWall) {
        long now = Time.simNow();
        return removedCars.stream()
                .filter(car -> ((Time.difference(now, car.end()) < timeWall)))
                .mapToLong(ICar::price)
                .sum();
    }

    @Override
    public long dailyEarnings() {
        return earningHelper(Time.MILLISECONDS_PER_DAY);
    }

    @Override
    public long weeklyEarnings() {
        return earningHelper(Time.MILLISECONDS_PER_WEEK);
    }


    @Override
    public HashMap<String, Long> currentCost() {
        HashMap<String, Long> cost = new HashMap<>();
        if (getCars().size() > 0) {
            for (ICar c : getCars()) {
                cost.put(c.license(), c.price());
            }
        }
        return cost;

    }


    public List<ICar> getCars() {
        return cars;
    }

    public List<ICar> getRemovedCars() {
        return removedCars;
    }


    @Override
    public List<ICar> getAllCars() {
        return Stream.concat(cars.stream(), removedCars.stream()).collect(Collectors.toList());
    }
}
