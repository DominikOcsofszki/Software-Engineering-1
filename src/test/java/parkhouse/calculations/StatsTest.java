package parkhouse.calculations;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatsTest {

    private final List<String[]> params = Data.paramsDuration();
    private List<ICar> carList = new ArrayList<>();
    private IParkingController parkingController;
    private static final int TEST_CAR_SUM = 10601;

    @BeforeEach
    void setUp() {
        parkingController = new ParkingController();
        for(String[] s : params) {
            carList.add(new Car(s));
        }
    }

    @AfterEach
    void tearDown() {
        parkingController = null;
        carList = null;
    }

    @Test
    @DisplayName("tests if the min price is right in a given list")
    void stats_minCars_test() {
        for(ICar car : carList) {
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(201, Stats.minCars(carList));
    }

    @Test
    @DisplayName("tests if the max price is right in a given list")
    void stats_maxCars_test() {
        for(ICar car : carList) {
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(4000, Stats.maxCars(carList));
    }

    @Test
    @DisplayName("tests if StatsAllCarsSum has the right sum")
    void abstractStats_sum_test() {
        for(ICar car : carList) {
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(TEST_CAR_SUM, Stats.sumCars(carList));
    }

    @Test
    @DisplayName("tests if StatsAllCarsAvg has the right avg")
    void statsAvgtest() {
        for(ICar car : carList) {
            parkingController.addCar(car);
            parkingController.removeCar(car);
        }
        assertEquals(TEST_CAR_SUM / params.size(), Stats.avgCars(carList));
    }




}
