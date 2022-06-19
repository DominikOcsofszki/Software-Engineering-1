package parkhouse.calculations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//class StatsAllCarsAvgTest {

class StatsAllCarsAvgTest {

    List<String[]> params = Data.params();
    IParkingController controller = new ParkingController();
    ICar car;
    StatsAllCarsAvg statsAllCarsAvg;

    @BeforeEach
    void beforeEach(){
        statsAllCarsAvg = new StatsAllCarsAvg(controller);
        car = new Car(params.get(0));

    }

    @AfterEach
    void tearDown() {
        car = null;
        statsAllCarsAvg = null;
    }

    @DisplayName("Divided by Zero if no Cars")
    @Test
    void divZeroIfEmpty_test() {
        assertEquals(0, statsAllCarsAvg.template1());
    }
    @DisplayName("sum test = To be implemented")
    @Test
    void sum_test() {
        //ToDO
//        controller.addCar();
        assertTrue(false);
    }

}