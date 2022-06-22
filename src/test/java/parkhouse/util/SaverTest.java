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

    IParkingController controller = new ParkingController();
    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if cars are saved and loaded correctly")
    public void saver_saveLoadCars_test() {     //ToDo: test? Errors only possible by wrong Path
        for (ICar c : cars) {
            controller.addCar(c);
        }
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        Saver.loadCars(controller, "MainServlet");
        for (ICar c : cars) {
            assertEquals(c.toString(),
                    Finder.findCar(controller.getAllCars(), ICar::ticket, c.ticket()).toString()
            );
        }
    }
    @Test
    @DisplayName("Test init()")
    public void init_Test() {
        for (ICar c : cars) {
            controller.addCar(c);
        }
        Saver.saveCars(controller, "MainServlet");
        controller = new ParkingController();
        assertTrue(Saver.init());
        if (Saver.init()) {
            assertFalse(true);
        }
        assertFalse(Saver.init());
    }
}