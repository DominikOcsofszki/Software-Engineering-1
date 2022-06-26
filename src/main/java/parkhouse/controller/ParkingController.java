package parkhouse.controller;

import parkhouse.car.ICar;
import parkhouse.commands.Commander;
import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.views.*;

import java.util.List;

public class ParkingController implements IParkingController {

    private final IParkingModel parkingModel;
    private final DailyEarningsView dailyEarningsView;
    private final WeeklyEarningsView weeklyEarningsView;
    private final CurrentCostsView currentCostsView;
    private final ClientCategoriesView clientCategoriesView;
    private final VehicleTypesView vehicleTypesView;
    private final EarningsByCategoriesView earningsByCategoriesView;
    private final DurationView durationView;
    private final Commander commander;


    public ParkingController() {
        parkingModel = new ParkingModel();
        dailyEarningsView = new DailyEarningsView(parkingModel);
        weeklyEarningsView = new WeeklyEarningsView(parkingModel);
        currentCostsView = new CurrentCostsView(parkingModel);
        clientCategoriesView = new ClientCategoriesView(parkingModel);
        vehicleTypesView = new VehicleTypesView(parkingModel);
        earningsByCategoriesView = new EarningsByCategoriesView(parkingModel);
        durationView = new DurationView(parkingModel);
        commander = new Commander();
    }

    public List<ICar> getCars() {
        return parkingModel.getCarList();
    }

    public List<ICar> getRemovedCars() {
        return parkingModel.getRemovedCarList();
    }

    public List<ICar> getAllCars() {
        return parkingModel.getAllCars();
    }

    @Override
    public void addCar(ICar car) {
        parkingModel.addCar(car);
    }

    @Override
    public void removeCar(ICar car) {   // _do
        parkingModel.removeCar(car);
    }

    @Override
    public void deleteCar(ICar car) {
        parkingModel.deleteCar(car);
    }

    //----------------
    @Override
    public void addCarRestartServer(ICar car) {
        parkingModel.addCarRestartServer(car);
    }

    @Override
    public void addRemovedCarRestartServer(ICar car) {
        parkingModel.removeCarRestartServer(car);
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
        parkingModel.notifyObservers();
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

    @Override
    public EarningsByCategoriesView earningsByCategoriesView() {
        return earningsByCategoriesView;
    }

    @Override
    public DurationView durationView() {
        return durationView;
    }

    public Commander commander() {
        return commander;
    }
}
