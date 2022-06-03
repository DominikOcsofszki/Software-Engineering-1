package parkhouse.controller;

import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

public class ParkingController implements IParkingController {

    private IParkingModel model;
    private DailyEarningsView dailyEarningsView;
    private WeeklyEarningsView weeklyEarningsView;
    private CurrentCostsView currentCostsView;

    public ParkingController() {
        this.model = new ParkingModel();
        this.dailyEarningsView = new DailyEarningsView(model);
        this.weeklyEarningsView = new WeeklyEarningsView(model);
        this.currentCostsView = new CurrentCostsView(model);
    }

    public void addCar(String[] params) {
        model.addCar(params);
    }

    public void removeCar(String[] params) {
        model.removeCar(params);
    }
}
