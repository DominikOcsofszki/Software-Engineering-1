package parkhouse.util;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.function.Function;
import java.util.stream.StreamSupport;

public class Finder {

    private Finder() {
    }

    public static ICar findCar(Iterable<ICar> iter, Function<ICar, Object> loc, Object id) {
        return StreamSupport.stream(iter.spliterator(), false)
                .filter(c -> (loc.apply(c).equals(id)))
                .findFirst().orElseThrow();
    }

}
