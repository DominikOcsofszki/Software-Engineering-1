package parkhouse.models;

import parkhouse.car.ICar;

import java.util.HashMap;
import java.util.List;

public interface IParkingModel extends IObservable {

    void addCar(ICar car); // _do
    void removeCar(ICar car);// _do
    List<ICar> getCars(); //_do
    List<ICar> getRemovedCars(); //_do
    List<ICar> getCarsAndRemovedCars(); //_do

    Double dailyEarnings();
    Double weeklyEarnings();
    HashMap<String,Double> currentCost();

    //-----
    void addCarRestartServer(ICar car); // _do
    void removeCarRestartServer(ICar car);// _do
    //-----
}
