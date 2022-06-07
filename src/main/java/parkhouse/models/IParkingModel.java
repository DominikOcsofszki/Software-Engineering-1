package parkhouse.models;

import parkhouse.car.ICar;

import java.util.HashMap;
import java.util.List;

public interface IParkingModel extends IObservable {

    void addCar(String[] params);
    void removeCar(String[] params);
    void addCar(ICar car); // _do
    void removeCar(ICar car);// _do
    List<ICar> getCars(); //_do
    List<ICar> getRemovedCars(); //_do
    List<ICar> getCarsAndRemovedCars(); //_do
//    long currentTimeByLastCar(); // old ToDo delete`?
    long lastTimeExixtOrEnterCar();

    Double dailyEarnings();
    Double weeklyEarnings();
    HashMap<String,Double> currentCost();

}
