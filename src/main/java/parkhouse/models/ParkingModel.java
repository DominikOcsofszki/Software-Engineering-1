package parkhouse.models;

import parkhouse.calculations.Price;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.util.Time;
import parkhouse.views.IObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingModel implements IParkingModel {

    private final List<ICar> cars;
    private final List<IObserver> observers;
    private final List<ICar> removedCars;
    private long lastTimeExixtOrEnterCar;
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
    @Override
    public void addCar(String[] params) {       //ToDo delete? ->Jakob
        cars.add(new Car(params));
        notifyObservers();


    }

    @Override
    public void addCar(ICar car) {  //added car with ICar _do
        cars.add(car);
        lastTimeExixtOrEnterCar = car.begin();         //For Time
        notifyObservers();
    }

    @Override
    public void removeCar(String[] params) {        //ToDo delete? ->Jakob
        removedCars.add(new Car(params));
        cars.removeIf(c -> c.license().equals(new Car(params).license()));
        notifyObservers();
    }

    @Override
    public void removeCar(ICar car) {   //_do
        removedCars.add(car);
        cars.remove(car);           //ToDo remove update of time in remove since calc not accurate?
        lastTimeExixtOrEnterCar = car.begin() + (car.duration() / (Integer.parseInt(Config.SIMULATION_SPEED)));     //For Time
        notifyObservers();
    }

    @Override
    public Double dailyEarnings() { //ToDo _
//        long now = Time.getTimeFromLastEnteredCarCheckBoth(getCars(), getRemovedCars());
        long now = lastTimeExixtOrEnterCar();

        double sum = 0;
        // functional way _do
        sum = removedCars.stream()
                .filter(car -> Time.difference(now, car.end()) < Time.MILLISECONDS_PER_DAY)
                .mapToDouble(ICar::price)
                .sum();
        return Price.out(sum);
    }

    @Override
    public Double weeklyEarnings() {    //ToDo _
        long now = lastTimeExixtOrEnterCar();
        double sum = 0D;
        // functional way _do
        sum = removedCars.stream()
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
//    public long currentTimeByLastCar() {        //ToDo Delete
//        long now = Time.getTimeFromLastEnteredCarCheckBoth(getCars(), getRemovedCars());
//        return now;
//    }

    @Override
    public long lastTimeExixtOrEnterCar() {
        return lastTimeExixtOrEnterCar;
    }

    @Override
    public HashMap<String, Double> currentCost() {
        //Hashmap for already paid and exited Cars
        /*
        HashMap<String, Double> cost = new HashMap<>();
        for (ICar c : getRemovedCars()) {
            cost.put(c.license(), Calc.calcInCent(c.price()));
        }
        */
        HashMap<String, Double> cost = new HashMap<>();
//        long now = Time.now();
        if(getCars().size() > 0) {
//            long now = Time.getTimeFromLastEnteredCarCheckBoth(getCars(), getRemovedCars());
            long now = lastTimeExixtOrEnterCar();
            for (ICar c : getCars()) {
                double priceCalc = Price.price(c, now);
                cost.put(c.license(), priceCalc);
            }
        }
        return cost;

    }
}
