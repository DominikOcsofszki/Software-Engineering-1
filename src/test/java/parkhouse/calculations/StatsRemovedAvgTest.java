package parkhouse.calculations;

import org.junit.jupiter.api.*;

import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatsRemovedAvgTest {

    List<String[]> params = Data.params();
    IParkingController controller = new ParkingController();
    ICar car;
    StatsRemovedAvg statsRemovedAvg;

    @BeforeEach
    void beforeEach(){
        statsRemovedAvg = new StatsRemovedAvg(controller);
        car = new Car(params.get(0));

    }

    @AfterEach
    void tearDown() {
        car = null;
        statsRemovedAvg = null;
    }

    @DisplayName("Divided by Zero if no Cars")
    @Test
    void divZeroIfEmpty_test() {
        assertEquals(0, statsRemovedAvg.template1());
    }
    @Test
    void sum_test() {
//        controller.addCar();
        assertTrue(false);
    }

}