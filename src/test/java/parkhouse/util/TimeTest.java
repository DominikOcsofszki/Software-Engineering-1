package parkhouse.util;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {
    IParkingController controller;
    List<ICar> cars;
    @BeforeEach
    void setUp(){
        controller = new ParkingController();
        cars = Data.cars();
    }
    @AfterEach
    void tearDown() {
        controller = null;
        cars = null;
    }
    @Test
    void time_difference_test() {
        assertEquals(0, Time.diff(50, 50));
        assertEquals(100, Time.diff(50, -50));
        assertEquals(200, Time.diff(50, -150));
        assertEquals(0, Time.diff(-50, -50));
        assertEquals(100, Time.diff(-50, 50));
        assertEquals(200, Time.diff(-150, 50));
    }



    @Test
    @DisplayName("Last entered time")
    public void lastEnterTime_test() {     //ToDo: test? Errors only possible by wrong Path
        for (ICar c : cars) {
            controller.addCar(c);
        }
        assertEquals(1655052012646L, Time.getTimeFromLastEnteredCar(controller.getCars()));

    }
    @Test
    @DisplayName("Last entered time")
    public void lastEnterTimeBoth_test() {     //ToDo: test? Errors only possible by wrong Path
        for (ICar c : cars) {
            controller.addCar(c);
        }
        assertEquals(1655052012646L, Time.getTimeFromLastEnteredCarCheckBoth(controller.getCars(), controller.getRemovedCars()));

    }

    @Test
    @DisplayName("Last entered time Both empty")
    public void lastEnterTimeBothEmpty_test() {     //ToDo: test? Errors only possible by wrong Path
        for (ICar c : cars) {
            controller.addCar(c);
        }
        assertThrows(IndexOutOfBoundsException.class,() -> Time.getTimeFromLastEnteredCarCheckBoth(new ArrayList<ICar>(), controller.getRemovedCars()));
        assertThrows(IndexOutOfBoundsException.class,() -> Time.getTimeFromLastEnteredCarCheckBoth(new ArrayList<ICar>(), new ArrayList<ICar>()));

    }
    @Test
    @DisplayName("test real Realtime")
    public void realTimeTest() {
        //ToDo is this test possible without running the whole system?
    }
}
