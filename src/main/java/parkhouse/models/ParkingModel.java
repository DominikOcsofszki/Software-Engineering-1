package parkhouse.models;

import parkhouse.car.ICar;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingModel implements IParkingModel {

    private final List<ICar> carList;
    private final List<IObserver> observerList;
    private final List<ICar> removedCarList;

    public ParkingModel() {
        carList = new ArrayList<>();
        observerList = new ArrayList<>();
        removedCarList = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observerList) {
            o.update();
        }
    }

    @Override
    public void addCar(ICar car) {
        carList.add(car);
        notifyObservers();
    }

    @Override
    public void removeCar(ICar car) {
        removedCarList.add(car);
        carList.remove(car);
        notifyObservers();
    }

    @Override
    public void deleteCar(ICar car) {
        carList.remove(car);
        removedCarList.remove(car);
        notifyObservers();
    }

    @Override
    public void removeCarRestartServer(ICar car) {
        removedCarList.add(car);
        notifyObservers();
    }

    private long earningHelper(long timeWall) {
        long now = Time.simNow();
        return removedCarList.stream()
                .filter(car -> (Time.diff(now, car.end()) < timeWall))
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
        if (getCarList().size() > 0) {
            for (ICar c : getCarList()) {
                cost.put(c.license(), c.price());
            }
        }
        return cost;

    }

    public List<ICar> getCarList() {
        return carList;
    }

    public List<ICar> getRemovedCarList() {
        return removedCarList;
    }

    @Override
    public List<ICar> getAllCars() {
        return Stream.concat(carList.stream(), removedCarList.stream()).collect(Collectors.toList());
    }
}
