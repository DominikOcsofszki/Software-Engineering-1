package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

import java.util.List;

public interface IParkingController {


    void addCar(ICar car);
    void removeCar(ICar car);

    List<ICar> getCars();
    List<ICar> getRemovedCars();


    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();


}
