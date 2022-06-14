package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class StatsAvg extends AbstractStats{


    @Override
    long calcExtra(IParkingController controller, long sum) {
        return sum / controller.getRemovedCars().size();
    }

}

