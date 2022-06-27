package parkhouse.calculations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Finder;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LocatorTest {

    IParkingController controller;
    List<ICar> cars = Data.cars();

    @BeforeEach
    public void setup() {
        controller = new ParkingController();
    }

    @AfterEach
    public void teardown() {
        controller = null;
    }

    @Test
    @DisplayName("Test if correct space is assigned to entering cars")
    public void locatorLocateTest() {
        Config.maxCars = 11;
        for (ICar c : cars) {
            if (!c.gone()) {
                c.setSpace(Locator.locate(controller));
                assertTrue(c.space() > 0 && c.space() <= Config.maxCars);
                assertThrows(
                        NoSuchElementException.class, () ->
                        Finder.findCar(controller.getCars(), ICar::space, c.space())
                );
                controller.addCar(c);
            }
        }
    }

    @Test
    @DisplayName("Test if -1 is returned if park house is full")
    public void locatorFullTest() {
        Config.maxCars = 0;
        assertEquals(-1, Locator.locate(controller));
    }

}
