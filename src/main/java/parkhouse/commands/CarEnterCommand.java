package parkhouse.commands;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class CarEnterCommand implements ICommand {

    private final ICar car;
    private final int spaceNr;
    private final IParkingController parkingController;

    public CarEnterCommand(ICar car, int spaceNr, IParkingController parkingController) {
        this.car = car;
        this.spaceNr = spaceNr;
        this.parkingController = parkingController;
    }

    @Override
    public void execute() {
        car.setSpace(spaceNr);
        parkingController.addCar(car); // adding the car
    }

    @Override
    public void undo() {
        parkingController.deleteCar(car);
    }
}
