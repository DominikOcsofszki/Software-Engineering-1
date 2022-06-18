package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.ArrayList;
import java.util.List;

public class StatsAllCarsSum extends AbstractStats {

    public StatsAllCarsSum(IParkingController controller) {
        super(controller);
    }

    @Override
    List<ICar> whichCars(IParkingController controller) {
        List<ICar> carsAll = new ArrayList<>(controller.getRemovedCars());
        carsAll.addAll(controller.getCars());
        return carsAll;
    }
}
