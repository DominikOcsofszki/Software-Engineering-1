package parkhouse.calculations;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatsTest {

    private final List<String[]> params = Data.paramsDuration();
    private IParkingController parkingController;

    @BeforeEach
    void setUp() {
        parkingController = new ParkingController();
    }

    @AfterEach
    void tearDown() {
        parkingController = null;
    }

    @Test
    @DisplayName("tests if the min price is right in a given list")
    void stats_minCars_test() {
        for(String[] s : params) {
            parkingController.addCar(new Car(s));
            parkingController.removeCar(new Car(s));
        }
        assertEquals(201, Stats.minCars(parkingController));
    }

    @Test
    @DisplayName("tests if the max price is right in a given list")
    void stats_maxCars_test() {
        for(String[] s : params) {
            parkingController.addCar(new Car(s));
            parkingController.removeCar(new Car(s));
        }
        assertEquals(4000, Stats.maxCars(parkingController));
    }

    @Test
    @DisplayName("tests if the min/max price is 0 when no cars entered")
    void stats_noCars_test() {
        assertEquals(0, Stats.maxCars(parkingController));
        assertEquals(0, Stats.minCars(parkingController));
    }


}
