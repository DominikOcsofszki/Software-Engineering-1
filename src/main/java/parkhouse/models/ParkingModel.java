package parkhouse.models;

import parkhouse.calculations.Calc;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
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
        cars = new ArrayList<>();       //cars still in the Parkhouse
        observers = new ArrayList<>();
        removedCars = new ArrayList<>();    //cars that have been in the Parkhouse -> paid
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
    public void addCar(ICar car) {  //added car with ICar _do
        cars.add(car);
        notifyObservers();
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
    public void removeCar(ICar car) {   //_do
        removedCars.add(car);
        cars.remove(car);
        notifyObservers();
    }

    @Override
    public Double dailyEarnings() { //ToDo _
        double sum = 100D;
        // functional way _do
        sum = removedCars.stream()
                .filter(car -> Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_DAY)
                .mapToDouble(ICar::price)
                .sum();
        //return sum;
        /*for(ICar car : removedCars) {
            if(Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_DAY) sum += car.price();
        }*/
        return sum;
    }

    @Override
    public Double weeklyEarnings() {    //ToDo _
        double sum = 0D;
        // functional way _do
        sum = removedCars.stream()
                .filter(car -> ((Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_WEEK)))
                .mapToDouble(ICar::price)
                .sum();
        //return sum; _do
        /*for(ICar car : removedCars) {     //ToDO check if new sum-function also works
            if(Time.difference(Time.now(), car.end()) < Time.MILLISECONDS_PER_WEEK) sum += car.price();
            else removedCars.remove(car);   // ToDo why is this needed?
        }*/
        return sum;
    }

    @Override
    public HashMap<String, Double> currentCost() {
        /* Jakob
        HashMap<String,Double> cost = new HashMap<>();
        long now = Time.now();
        for (ICar c : cars) {
            cost.put(c.license(), ((now - c.begin()) / 60000d) * Config.PRICE);
        }
        return cost;*/

        //Hashmap for already paid and exited Cars
        HashMap<String, Double> cost = new HashMap<>();
        for (ICar c : getRemovedCars()) {
            cost.put(c.license(), Calc.calcInCent(c.price()));
        }
        //...
//        long now = Time.now();
        if(getCars().size() > 0) {
            long now = Time.getTimeFromLastEnteredCar(getCars());
            for (ICar c : getCars()) {
                System.out.println("now: " + now + " c.begin(): " + c.begin());
                double priceCalc = ((now - c.begin()) / 60000d) * Config.PRICE;
                cost.put(c.license(), priceCalc);

            }
        }
        return cost;

    }
}
