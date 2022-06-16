package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.ArrayList;
import java.util.List;

public class StatsAllCarsSum extends AbstractStats {

    @Override
    List<ICar> whichCars(IParkingController controller) {
        List<ICar> carsAll = new ArrayList<>(controller.getRemovedCars());
        carsAll.addAll(controller.getCars());
        return carsAll;
    }

//    @Override
//    double calcExtra(int sizeCarsList, double sum) {
//        return sum;
//    }
}
