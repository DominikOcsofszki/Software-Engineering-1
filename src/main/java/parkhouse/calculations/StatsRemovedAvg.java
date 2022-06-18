package parkhouse.calculations;

import parkhouse.controller.IParkingController;

public class StatsRemovedAvg extends StatsRemovedSum{

    public StatsRemovedAvg(IParkingController controller) {
        super(controller);
    }

    @Override
    long calcExtra(int sizeCarsList, long sum) {
        return sizeCarsList != 0 ? sum/sizeCarsList : 0;
    }
}
