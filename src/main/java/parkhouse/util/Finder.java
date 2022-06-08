package parkhouse.util;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.util.NoSuchElementException;
import java.util.function.Function;

public class Finder {

    private Finder() {
    }

    public static ICar findCar(Iterable<ICar> iter, Function<ICar, Object> loc, Object id) {
        for (ICar c : iter) {
            if (loc.apply(c).equals(id)) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }

    public ICar findICarForTicket(IParkingController controller, String plateSearching) {
        return controller.getCars().stream().
                filter(car -> (car.ticket().equals(plateSearching)))
                .findFirst().orElseThrow();
    }

}
