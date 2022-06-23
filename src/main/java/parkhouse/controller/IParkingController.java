package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.commands.Commander;
import parkhouse.commands.ICommand;
import parkhouse.views.*;

import java.util.List;

public interface IParkingController {

    void addCar(ICar car);
    void removeCar(ICar car);
    void deleteCar(ICar car);

    void addCarRestartServer(ICar car); // ToDo delete this function?
    void addRemovedCarRestartServer(ICar car);// _do

    List<ICar> getCars();
    List<ICar> getRemovedCars();
    List<ICar> getAllCars();

    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();
    ClientCategoriesView clientCategoriesView();
    VehicleTypesView vehicleTypeView();
    EarningsByCategoriesView earningsByCategoriesView();
    DurationView durationView();
    Commander commander();
}
