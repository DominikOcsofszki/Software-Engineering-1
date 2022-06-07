package parkhouse.models;

import parkhouse.car.ICar;

import java.util.HashMap;
import java.util.List;

public interface IParkingModel extends IObservable {

    void addCar(String[] params);
    void removeCar(String[] params);
    void addCar(ICar car); // _do
    void removeCar(ICar car);// _do
    public List<ICar> getCars(); //_do
    public List<ICar> getRemovedCars(); //_do
    public List<ICar> getCarsAndRemovedCars(); //_do


    Double dailyEarnings();
    Double weeklyEarnings();
    HashMap<String,Double> currentCost();

}
