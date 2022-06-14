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

