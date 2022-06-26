package parkhouse.models;

import parkhouse.car.ICar;

import java.util.HashMap;
import java.util.List;

public interface IParkingModel extends IObservable {

    void addCar(ICar car); // _do
    void removeCar(ICar car);// _do
    void deleteCar(ICar car);
    List<ICar> getCarList(); //_do
    List<ICar> getRemovedCarList(); //_do
    List<ICar> getAllCars(); //_do

    long dailyEarnings();
    long weeklyEarnings();
    HashMap<String,Long> currentCost();

    //-----
    void addCarRestartServer(ICar car); // _do
    void removeCarRestartServer(ICar car);// _do
    //-----
}
