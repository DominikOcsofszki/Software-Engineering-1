package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.commands.Commander;
import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.*;

import java.util.List;

public class ParkingController implements IParkingController {

    private final IParkingModel model;
    private final DailyEarningsView dailyEarningsView;
    private final WeeklyEarningsView weeklyEarningsView;
    private final CurrentCostsView currentCostsView;
    private final ClientCategoriesView clientCategoriesView;
    private final VehicleTypesView vehicleTypesView;
    private final Commander commander;


    public ParkingController() {
        model = new ParkingModel();
        dailyEarningsView = new DailyEarningsView(model);
        weeklyEarningsView = new WeeklyEarningsView(model);
        currentCostsView = new CurrentCostsView(model);
        clientCategoriesView = new ClientCategoriesView(model);
        vehicleTypesView = new VehicleTypesView(model);
        commander = new Commander();
    }

    public List<ICar> getCars() {
        return model.getCars();
    }

    public List<ICar> getRemovedCars() {
        return model.getRemovedCars();
    }

    public List<ICar> getAllCars() {
        return model.getAllCars();
    }

    @Override
    public void addCar(ICar car) {
        model.addCar(car);
    }

    @Override
    public void removeCar(ICar car) {   // _do
        model.removeCar(car);
    }

    @Override
    public void deleteCar(ICar car) {
        model.deleteCar(car);
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
        return dailyEarningsView;
    }

    @Override
    public WeeklyEarningsView weeklyEarningsView() {
        return weeklyEarningsView;
    }

    @Override
    public CurrentCostsView currentCostView() {
        model.notifyObservers();
        return currentCostsView;
    }

    @Override
    public ClientCategoriesView clientCategoriesView() {
        return clientCategoriesView;
    }

    @Override
    public VehicleTypesView vehicleTypeView() {
        return vehicleTypesView;
    }

    public Commander commander() {
        return commander;
    }
}
