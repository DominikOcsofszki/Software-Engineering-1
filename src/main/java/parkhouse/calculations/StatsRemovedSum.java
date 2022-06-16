package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public class StatsRemovedSum extends AbstractStats {


    @Override
    List<ICar> whichCars(IParkingController controller) {
        return controller.getRemovedCars();
    }

//    @Override
//    double calcExtra(int sizeCarsList, double sum) {
//        return sum;
//    }
}
