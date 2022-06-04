package parkhouse.controller;

import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

public class ParkingController implements IParkingController {

    private final IParkingModel model;
    private final DailyEarningsView dailyEarningsView;
    private final WeeklyEarningsView weeklyEarningsView;
    private final CurrentCostsView currentCostsView;

    public ParkingController() {
        this.model = new ParkingModel();
        this.dailyEarningsView = new DailyEarningsView(model);
        this.weeklyEarningsView = new WeeklyEarningsView(model);
        this.currentCostsView = new CurrentCostsView(model);
    }

    @Override
    public void addCar(String[] params) {
        model.addCar(params);
    }

    @Override
    public void removeCar(String[] params) {
        model.removeCar(params);
    }

    @Override
    public DailyEarningsView dailyEarningsView() {
        return this.dailyEarningsView;
    }

    @Override
    public WeeklyEarningsView weeklyEarningsView() {
        return this.weeklyEarningsView;
    }

    @Override
    public CurrentCostsView currentCostView() {
        return this.currentCostsView;
    }
}
