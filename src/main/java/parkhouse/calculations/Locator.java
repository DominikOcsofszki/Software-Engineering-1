package parkhouse.calculations;

import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;

import java.util.List;
import java.util.stream.Collectors;

public class Locator {

    public static int locate(IParkingController controller) {
        List<Integer> occupied = controller.getCars()
                .stream().mapToInt(ICar::space)
                .boxed().collect(Collectors.toList());

        for (int s = 1; s <= Config.maxCars; s++) {
            if (!occupied.contains(s)) {
                return s;
            }
        }
        return -1;
    }

}
