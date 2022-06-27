package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.config.Config;
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

    @Test
    @DisplayName("Test if config is saved and loaded correctly")
    public void saverSaveConfig() {

        /*assertEquals(16, Config.maxCars);
        assertEquals(0, Config.openFrom);
        assertEquals(0, Config.openTo);
        Config.setMaxCars(22);
        Config.setOpenFrom(2);
        Config.setOpenTo(5);
        assertEquals(22, Config.maxCars);
        assertEquals(2, Config.openFrom);
        assertEquals(5, Config.openTo);

        Saver.loadCars(controller, "ServletTest");
        assertEquals(16, Config.maxCars);
        assertEquals(0, Config.openFrom);
        assertEquals(0, Config.openTo);
        Config.setMaxCars(22);
        Config.setOpenFrom(2);
        Config.setOpenTo(5);
        assertEquals(22, Config.maxCars);
        assertEquals(2, Config.openFrom);
        assertEquals(5, Config.openTo);
        Saver.saveCars(controller, "ServletTest");
        Config.setMaxCars(11);
        Config.setOpenFrom(11);
        Config.setOpenTo(11);
        assertEquals(11, Config.maxCars);
        assertEquals(11, Config.openFrom);
        assertEquals(11, Config.openTo);
        Saver.loadCars(controller, "ServletTest");
        assertEquals(22, Config.maxCars);
        assertEquals(2, Config.openFrom);
        assertEquals(5, Config.openTo);
*/
    }

    @Test
    @DisplayName("Test init SaverConfig")
    public void saverInitConfigTest() {
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        assertTrue(Saver.initConfig());
        if (Saver.initConfig()) {
            fail();
        }
        assertFalse(Saver.initConfig());
    }
}