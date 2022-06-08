package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.views.CurrentCostsView;
//import parkhouse.views.CurrentElementsView;     // ToDo Still needed? Seems unnecessary!
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

import java.util.List;

public interface IParkingController {


    void addCar(ICar car); //_do
    void removeCar(ICar car);   //_do

    List<ICar> getCars(); //_do
    List<ICar> getRemovedCars(); //_do


    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();
//    CurrentElementsView currentElementsView();    // ToDo Still needed? Seems unnecessary!


}
