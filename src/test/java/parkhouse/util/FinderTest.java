package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class FinderTest {

    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if correct car is found by nr")
    public void finder_findCarByNr_test() {
        for (ICar c : cars) {
            assertEquals(c, Finder.findCar(cars, ICar::nr, c.nr()));
        }
    }

    @Test
    @DisplayName("Test if correct car is found by license")
    public void finder_findCarByLicense_test() {
        for (ICar c : cars) {
            assertEquals(c, Finder.findCar(cars, ICar::license, c.license()));
        }
    }

    @Test
    @DisplayName("Test if method throws the expected exception")
    public void finder_findException_test() {
        assertThrows(NoSuchElementException.class, () -> Finder.findCar(cars, ICar::nr, -1));
    }
}
