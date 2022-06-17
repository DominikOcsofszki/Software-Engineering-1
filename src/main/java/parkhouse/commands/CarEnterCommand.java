package parkhouse.commands;

import java.util.logging.Level;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.logging.*;

public class CarEnterCommand implements ICommand {

    private final ICar car;
    private final IParkingController controller;

    public CarEnterCommand(ICar car, IParkingController controller) {
        this.car = car;
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addCar(car);
        Log.getLogger().log(Level.FINE, "Car entered: " + car.license());
    }

    @Override
    public void undo() {
        controller.deleteCar(car);
        Log.getLogger().log(Level.FINE, "Car deleted: " + car.license());
    }
}
