package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;

public abstract class AbstractStats {

    public double template1(IParkingController controller) {
        double sum = sum(controller);
        double d = calcExtra(controller, sum);
        if (Config.isConfigDebugMode()) {
            debugPrint(d);
        }
        optionalPrintSthOrSo(controller, d);   //Can be empty
        return d;
    }

    void debugPrint(double d) {
        System.out.println("DEBUG(AbstractStats):ON" + d);
    }

    abstract double calcExtra(IParkingController controller, double sum);

    //    abstract double optional(IParkingController controller, double sum);
    void optionalPrintSthOrSo(IParkingController controller, double sum) { //Also possible as output to console out.print
    //Here nothing happends
    }

    public double sum(IParkingController controller) {
        return controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
    }
}


  /*  public double template1(IParkingController controller) {
        double sum = controller.getRemovedCars().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
        sum = optional(controller, sum);
        return sum;
    }*/
