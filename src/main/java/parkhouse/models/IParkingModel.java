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
    //long lastTimeExixtOrEnterCar();

    Double dailyEarnings();
    Double weeklyEarnings();
    HashMap<String,Double> currentCost();

}
