package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

public class StatsAvg extends AbstractStats{


    @Override
    double calcExtra(IParkingController controller, double sum) {
        return sum / controller.getRemovedCars().size();
    }

}

