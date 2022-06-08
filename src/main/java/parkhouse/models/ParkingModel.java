package parkhouse.models;

import parkhouse.calculations.Price;
import parkhouse.car.ICar;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public void removeCar(ICar car) {   //_do
        removedCars.add(car);
        cars.remove(car);
        notifyObservers();
    }

    @Override
    public Double dailyEarnings() { //ToDo _
        long now = Time.simNow();
        // functional way _do
        double sum = removedCars.stream()
                .filter(car -> Time.difference(now, car.end()) < Time.MILLISECONDS_PER_DAY)
                .mapToDouble(ICar::price)
                .sum();
        return Price.out(sum);
    }

    @Override
    public Double weeklyEarnings() {    //ToDo _
        long now = Time.simNow();
        // functional way _do
        double sum = removedCars.stream()
                .filter(car -> ((Time.difference(now, car.end()) < Time.MILLISECONDS_PER_WEEK)))
                .mapToDouble(ICar::price)
                .sum();
        /*sum = removedCars.stream()
                .filter(car -> ((Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_WEEK)))
                .mapToDouble(ICar::price)
                .sum();*/
        //return sum; _do
        /*for(ICar car : removedCars) {     //ToDO check if new sum-function also works
            if(Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_WEEK) sum += car.price();
            else removedCars.remove(car);   // ToDo why is this needed?
        }*/
//        System.out.println("weekly: "+sum);

        return Price.out(sum);
    }

    @Override
    public HashMap<String, Double> currentCost() {
        HashMap<String, Double> cost = new HashMap<>();
        if(getCars().size() > 0) {
            for (ICar c : getCars()) {
                cost.put(c.license(), Price.price(c));
            }
        }
        return cost;

    }

    // _do
    // ToDo: does it make sense to work on these? Needed getter to get the cars for calc like in cars()?
    public List<ICar> getCars() {
        return cars;
    }

    public List<ICar> getRemovedCars() {
        return removedCars;
    }

    @Override
    public List<ICar> getCarsAndRemovedCars() {     // produces a new List, might change the order of elements in List.
        final List<ICar> carsAndremovedCars; //_do

        carsAndremovedCars = new ArrayList<>(cars); //_do
        carsAndremovedCars.addAll(removedCars);

        return carsAndremovedCars;      //ToDo _do: ohters: decide if it makes sense to always produce a new List?
    }
    //_do
}
