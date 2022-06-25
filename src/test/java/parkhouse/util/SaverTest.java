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

    IParkingController controller = Data.controller();
    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if cars are saved and loaded correctly")
    public void saver_saveLoadCars_test() {
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        Saver.loadCars(controller, "MainServlet");
        for (ICar c : cars) {
            assertEquals(c.toString(),
                    Finder.findCar(controller.getAllCars(), ICar::ticket, c.ticket()).toString()
            );
        }
        Saver.saveCars(controller, "x");
        Saver.loadCars(controller, "y");
    }

    @Test
    @DisplayName("Test init()")
    public void init_Test() {
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        assertTrue(Saver.init());
        if (Saver.init()) {
            fail();
        }
        assertFalse(Saver.init());
    }
}