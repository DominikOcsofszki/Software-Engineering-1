package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaverTest {

    IParkingController controller = new ParkingController();
    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if cars are saved and loaded correctly")
    public void saver_saveLoadCars_test() {
        for (ICar c : cars) {
            controller.addCar(c);
        }
        Saver.saveCars(controller);
        controller = new ParkingController();
        Saver.loadCars(controller);
        for (ICar c : cars) {
            assertEquals(c.toString(),
                    Finder.findCar(controller.getAllCars(), ICar::ticket, c.ticket()).toString()
            );
        }
    }
}