package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public class StatsInHouseSum extends AbstractStats{     //ToDo delete since we do not calc stats of parking cars?
    public StatsInHouseSum(IParkingController controller) { //ToDo Or we can copy it from the currentCost()?
        super(controller);
    }

    @Override
    List<ICar> whichCars(IParkingController controller) {
        return controller.getCars();
    }
}
