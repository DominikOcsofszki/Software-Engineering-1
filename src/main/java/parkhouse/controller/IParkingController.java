package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

import java.util.List;

public interface IParkingController {

    void addCar(String[] params);
    void removeCar(String[] params);

    void addCar(ICar car); //_do
    void removeCar(ICar car);   //_do
    public List<ICar> getCars(); //_do
    public List<ICar> getRemovedCars(); //_do


    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();

}
