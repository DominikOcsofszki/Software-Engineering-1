package parkhouse.util;

import parkhouse.car.ICar;

import java.util.NoSuchElementException;
import java.util.function.Function;

public class Finder {

    public static ICar findCar(Iterable<ICar> iter, Function<ICar,Object> loc, Object id) {
        for (ICar c : iter) {
            if (loc.apply(c).equals(id)) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }
}