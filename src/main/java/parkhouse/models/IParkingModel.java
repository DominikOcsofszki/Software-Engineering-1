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

    long dailyEarnings();
    long weeklyEarnings();
    HashMap<String,Long> currentCost();

    //-----
    void addCarRestartServer(ICar car); // _do
    void removeCarRestartServer(ICar car);// _do
    //-----
}
