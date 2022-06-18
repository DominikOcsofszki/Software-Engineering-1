package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public abstract class AbstractStats {       //ToDo make a Multiton out ot the Stats Classes.
    IParkingController controller;
    public AbstractStats(IParkingController controller) {//ToDo does it make sense
        this.controller = controller;
    }

//    public long template1(IParkingController controller) { //ToDo Controller could be put in Constructor
    public long template1() {

        List<ICar> carsList = whichCars(controller);
        int sizeCarsList = carsList.size();
        long sum = sumCalcCars(carsList);
        return calcExtra(sizeCarsList, sum);
    }

    abstract List<ICar> whichCars(IParkingController controller);

    long calcExtra(int sizeCarsList, long sum) {
        return sum;
    }

    public long sumCalcCars(List<ICar> getCarsBothPossible) {
        return getCarsBothPossible.stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0L, Long::sum);
    }
}