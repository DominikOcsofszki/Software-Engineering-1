package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.views.*;

import java.util.List;

public interface IParkingController {


    void addCar(ICar car);
    void removeCar(ICar car);
    //-----
    void addCarRestartServer(ICar car); // _do
    void addRemovedCarRestartServer(ICar car);// _do
    //-----

    List<ICar> getCars();
    List<ICar> getRemovedCars();
    List<ICar> getAllCars();


    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();
    ClientCategoriesView clientCategoriesView();
    VehicleTypesView vehicleTypeView();
}
