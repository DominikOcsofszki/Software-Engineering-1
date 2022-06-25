package parkhouse.views;

import parkhouse.car.Car;
import parkhouse.car.ICar;

import org.junit.jupiter.api.*;
import parkhouse.models.IParkingModel;
import parkhouse.models.ParkingModel;
import parkhouse.util.Time;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyEarningsViewTest {

    private IParkingModel parkingModel;
    private WeeklyEarningsView weeklyEarningsView;
    private final ICar car = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
            "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});

    @BeforeEach
    void setUp() {
        parkingModel = new ParkingModel();
        weeklyEarningsView = new WeeklyEarningsView(parkingModel);
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
        weeklyEarningsView = null;
    }

    @Test
    @DisplayName("test if the weeklyEarningsView gets updated")
    void dailyEarningView_getDailyEarnings_test() {
        parkingModel.addCar(car);
        parkingModel.removeCar(car);
        assertEquals(69, weeklyEarningsView.getWeeklyEarnings());
    }

    @Test
    @DisplayName("test if the format is right")
    void dailyEarningsView_toString_test() {
        parkingModel.addCar(car);
        parkingModel.removeCar(car);
        assertEquals("0.69€", weeklyEarningsView.toString());
    }

}