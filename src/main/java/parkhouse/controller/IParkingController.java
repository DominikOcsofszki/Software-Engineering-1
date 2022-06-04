package parkhouse.controller;

import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

public interface IParkingController {

    void addCar(String[] params);
    void removeCar(String[] params);

    DailyEarningsView dailyEarningsView();
    WeeklyEarningsView weeklyEarningsView();
    CurrentCostsView currentCostView();

}
