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
//    private final CurrentElementsView currentElementsView; //_do    // ToDo Still needed? Seems unnecessary!


    public ParkingController() {
        model = new ParkingModel();
        dailyEarningsView = new DailyEarningsView(model);
        weeklyEarningsView = new WeeklyEarningsView(model);
        currentCostsView = new CurrentCostsView(model);
//        currentElementsView = new CurrentElementsView(model); //_do    // ToDo Still needed? Seems unnecessary!

    }

    // _do
    public List<ICar> getCars() {
        return model.getCars();
    }

    public List<ICar> getRemovedCars() {
        return model.getRemovedCars();
    }
    //_do

    @Override
    public void addCar(ICar car) {      //_do
        model.addCar(car);

    }

    @Override
    public void removeCar(ICar car) {   // _do
        model.removeCar(car);
    }

//    @Override    // ToDo Still needed? Seems unnecessary!
//    public CurrentElementsView currentElementsView() {
//        return this.currentElementsView;
//    }
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

    public IParkingModel parkingModel(){
        return model;
    }
}
