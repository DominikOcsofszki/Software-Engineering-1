package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public class StatsRemovedSum extends AbstractStats {


    public StatsRemovedSum(IParkingController controller) {
        super(controller);
    }

    @Override
    List<ICar> whichCars(IParkingController controller) {
        return controller.getRemovedCars();
    }
}
