package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.CurrentCostsView;
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

import java.util.List;

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

    // _do
    // ToDo: does it make sense to work on these? Needed getter to get the cars for calc like in cars()?
    public List<ICar> getCars() {
        return model.getCars();
    }

    public List<ICar> getRemovedCars() {
        return model.getRemovedCars();
    }
    //_do

    @Override
    public void addCar(String[] params) {
        model.addCar(params);
    }

    @Override
    public void removeCar(String[] params) {
        model.removeCar(params);
    }

    @Override
    public void addCar(ICar car) {      //_do
        model.addCar(car);

    }

    @Override
    public void removeCar(ICar car) {   // _do
        model.removeCar(car);

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
