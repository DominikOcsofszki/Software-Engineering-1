package parkhouse.util;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    /*
    TODO: Author: staher2s
     */

    private IParkingController controller;
    private List<ICar> cars;

    @BeforeEach
    void setup(){
        controller = new ParkingController();
        cars = Data.cars();
    }

    @AfterEach
    void teardown() {
        controller = null;
        cars = null;
    }

    @Test
    void timeDifferenceTest() {
        assertEquals(0, Time.diff(50, 50));
        assertEquals(100, Time.diff(50, -50));
        assertEquals(200, Time.diff(50, -150));
        assertEquals(0, Time.diff(-50, -50));
        assertEquals(100, Time.diff(-50, 50));
        assertEquals(200, Time.diff(-150, 50));
    }

}
