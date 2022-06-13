package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.CurrentCostsView;
//import parkhouse.views.CurrentElementsView; //_do    // ToDo Still needed? Seems unnecessary!
import parkhouse.views.DailyEarningsView;
import parkhouse.views.WeeklyEarningsView;

import java.util.List;

public class ParkingController implements IParkingController {

    private final IParkingModel model;
    private final DailyEarningsView dailyEarningsView;
    private final WeeklyEarningsView weeklyEarningsView;
    private final CurrentCostsView currentCostsView;


    public ParkingController() {
        model = new ParkingModel();
        dailyEarningsView = new DailyEarningsView(model);
        weeklyEarningsView = new WeeklyEarningsView(model);
        currentCostsView = new CurrentCostsView(model);

    }

    public List<ICar> getCars() {
        return model.getCars();
    }

    public List<ICar> getRemovedCars() {
        return model.getRemovedCars();
    }

    @Override
    public void addCar(ICar car) {
        model.addCar(car);
    }

    @Override
    public void removeCar(ICar car) {   // _do
        model.removeCar(car);
    }

    //----------------
    @Override
    public void addCarRestartServer(ICar car) {
        model.addCarRestartServer(car);
    }

    @Override
    public void addRemovedCarRestartServer(ICar car) {
        model.removeCarRestartServer(car);
    }
    //------------

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

    public IParkingModel parkingModel() {
        return model;
    }
}
