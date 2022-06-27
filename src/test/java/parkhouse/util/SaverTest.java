package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest {

    /*
    Author: docsof2s
     */

    private IParkingController controller = Data.controller();
    private final List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if cars are saved and loaded correctly")
    public void saverSaveLoadCarsTest() {
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        Saver.loadCars(controller, "MainServlet");
        for (ICar c : cars) {
            assertEquals(c.toString(),
                    Finder.findCar(controller.getAllCars(), ICar::ticket, c.ticket()).toString()
            );
        }
        Saver.loadCars(controller, "x");
    }

    @Test
    @DisplayName("Test init")
    public void saverInitTest() {
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        assertTrue(Saver.init());
        if (Saver.init()) {
            fail();
        }
        assertFalse(Saver.init());
    }
}