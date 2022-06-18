package parkhouse.calculations;

import parkhouse.controller.IParkingController;

public class StatsAllCarsAvg extends StatsAllCarsSum {

    public StatsAllCarsAvg(IParkingController controller) {
        super(controller);
    }

    @Override
    long calcExtra(int sizeCarsList, long sum) {
        return sizeCarsList != 0 ? sum/sizeCarsList : 0;
    }

}



