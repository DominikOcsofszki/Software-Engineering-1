package parkhouse.calculations;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocatorTest {

    IParkingController controller = new ParkingController();
    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if correct space is assigned to entering cars")
    public void locator_locate_test() {
        for (ICar c : cars) {
            if (!c.gone()) {
                c.setSpace(Locator.locate(controller));
                assertTrue(c.space() <= Config.maxCars);
                assertThrows(
                        NoSuchElementException.class, () ->
                        Finder.findCar(controller.getCars(), ICar::space, c.space())
                );
                controller.addCar(c);
            }
        }
    }

}
