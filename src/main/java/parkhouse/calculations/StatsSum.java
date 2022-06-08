package parkhouse.calculations;

import parkhouse.controller.IParkingController;

public class StatsSum extends AbstractStats {

    @Override
    double optional(IParkingController controller, double sum) {
        return sum;
    }
}
