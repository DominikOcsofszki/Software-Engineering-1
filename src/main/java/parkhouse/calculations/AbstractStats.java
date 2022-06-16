package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.List;

public abstract class AbstractStats {

    public long template1(IParkingController controller) {

        List<ICar> carsList = whichCars(controller);
        int sizeCarsList = carsList.size();
        long sum = sumCalcCars(carsList);
        long d = calcExtra(sizeCarsList, sum);

        return d;
    }
  /*  public double templateCars(IParkingController controller) {
        List<ICar> removedCars= controller.getRemovedCars();
        List<ICar> carsInHouse= controller.getCars();
        double sum = sumCalc(controller);
        double d = calcExtra(controller, sum);

        return d;
    }*/

    abstract List<ICar> whichCars(IParkingController controller);

    long calcExtra(int sizeCarsList, long sum) {
        return sum;
    }


/*    public double sumCalc(IParkingController controller) {
        return controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
    }*/

    public long sumCalcCars(List<ICar> getCarsBothPossible) {
        return getCarsBothPossible.stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0L, Long::sum);
    }
}







/////--- OLD!
/*
package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;

public abstract class AbstractStats {

    public long template1(IParkingController controller) {
        long sum = sum(controller);
        long d = calcExtra(controller, sum);
        if (Config.isConfigDebugMode()) {
            debugPrint(d);
        }
        optionalPrintSthOrSo(controller, d);   //Can be empty
        return d;
    }

    void debugPrint(long d) {
        System.out.println("DEBUG(AbstractStats):ON" + d);
    }

    abstract long calcExtra(IParkingController controller, long sum);

    void optionalPrintSthOrSo(IParkingController controller, long sum) {
    //Here nothing happends
    }

    public long sum(IParkingController controller) {
        return controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0L, Long::sum);
    }
}

*/
