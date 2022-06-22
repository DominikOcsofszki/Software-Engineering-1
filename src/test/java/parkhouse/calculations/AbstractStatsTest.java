package parkhouse.calculations;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.List;

public class AbstractStatsTest {

    private final List<String[]> params = Data.paramsDuration();
    private IParkingController parkingController;
    private static final int TEST_CAR_SUM = 10601;

    @BeforeEach
    void setUp() {
        parkingController = new ParkingController();
    }

    @AfterEach
    void tearDown() {
        parkingController = null;
    }


    @Test
    @DisplayName("tests if StatsAllCarsSum has the right sum")
    void abstractStats_sum_test() {
        for(String[] s : params) {
            ICar car = new Car(s);
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(TEST_CAR_SUM, new StatsAllCarsSum(parkingController).template1());
    }

    @Test
    @DisplayName("tests if StatsAllCarsAvg has the right avg")
    void statsAvgtest() {
        for(String[] s : params) {
            ICar car = new Car(s);
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(TEST_CAR_SUM / params.size(), new StatsAllCarsAvg(parkingController).template1());
    }

    @Test
    @DisplayName("tests if StatsInHouseSum has the right sum")
    void statsInHouseSum_test() {
        for(String[] s : params) {
            parkingController.addCar(new Car(s));
        }
        assertEquals(TEST_CAR_SUM, new StatsInHouseSum(parkingController).template1());
        int tmp = TEST_CAR_SUM;
        while(parkingController.getCars().size() > 0) {
            ICar c = parkingController.getCars().get(0);
            parkingController.getCars().remove(c);
            tmp -= c.price();
            assertEquals(tmp, new StatsInHouseSum(parkingController).template1());
        }
    }

    @Test
    @DisplayName("tests if StatsInHouseAvg has the right sum")
    void statsInHouseAvg_test() {
        for(String[] s : params) {
            parkingController.addCar(new Car(s));
        }
        assertEquals(TEST_CAR_SUM / parkingController.getCars().size(), new StatsInHouseAvg(parkingController).template1());
        int tmp = TEST_CAR_SUM;
        while(parkingController.getCars().size() > 1) {
            ICar c = parkingController.getCars().get(0);
            parkingController.getCars().remove(c);
            tmp -= c.price();
            assertEquals(tmp / parkingController.getCars().size(), new StatsInHouseAvg(parkingController).template1());
        }
    }

    @Test
    @DisplayName("tests if StatsRemovedSum has the right sum")
    void statsRemovedSum_test() {
        for(String[] s : params) {
            ICar car = new Car(s);
            parkingController.addCar(car);
        }
        long tmp = 0;
        while(parkingController.getCars().size() > 0) {
            ICar c = parkingController.getCars().get(0);
            parkingController.removeCar(c);
            tmp += c.price();
            assertEquals(tmp, new StatsRemovedSum(parkingController).template1());
        }
        assertEquals(TEST_CAR_SUM, tmp);
    }

    @Test
    @DisplayName("tests if StatsRemovedAvg has the right sum")
    void statsRemovedAvg_test() {
        for(String[] s : params) {
            ICar car = new Car(s);
            parkingController.addCar(car);
        }
        long tmp = 0;
        while(parkingController.getCars().size() > 0) {
            ICar c = parkingController.getCars().get(0);
            parkingController.removeCar(c);
            tmp += c.price();
            assertEquals(tmp / parkingController.getRemovedCars().size(), new StatsRemovedAvg(parkingController).template1());
        }
        assertEquals(TEST_CAR_SUM, tmp);
    }

}