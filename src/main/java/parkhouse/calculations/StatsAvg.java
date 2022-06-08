package parkhouse.calculations;

import parkhouse.controller.IParkingController;

public class StatsAvg extends AbstractStats{

    @Override
    double optional(IParkingController controller, double sum) {
        return sum / controller.getRemovedCars().stream().filter(x -> (x.price() > 0)).count();
    }
}
