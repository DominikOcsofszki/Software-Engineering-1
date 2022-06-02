package parkhouse.util;

import parkhouse.car.CarIF;

import java.util.NoSuchElementException;
import java.util.function.Function;

public class Finder {

    public static CarIF findCar(Iterable<CarIF> iter, Function<CarIF,Object> loc, Object id) {
        for (CarIF c : iter) {
            if (loc.apply(c).equals(id)) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }
}
