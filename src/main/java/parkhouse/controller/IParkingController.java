package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

public interface IParkingController {

    void addCar(String[] params);
    void removeCar(String[] params);

    void addCar(ICar car);
    void removeCar(ICar car);

    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();

}
