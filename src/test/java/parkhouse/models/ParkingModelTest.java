package parkhouse.models;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.util.Time;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingModelTest {

    private ParkingModel parkingModel;
    private final List<String[]> params = Data.paramsDuration();
    private final List<String[]> params2 = Data.params();
    private final List<ICar> carsList = new ArrayList<>();
    private final List<ICar> carsNoDurationList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        parkingModel = new ParkingModel();
        for(String[] s : params) {
            carsList.add(new Car(s));
        }
        for(String[] s : params2) {
            carsNoDurationList.add(new Car(s));
        }
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
    }

    @Test
    @DisplayName("test if the addCar method adds the right cars to the list")
    void parkingModel_addCar_test() {
        for(String[] s : params) {
            parkingModel.addCar(new Car(s));
        }
        assertEquals(params.size(), parkingModel.getCars().size());
        for(int i = 0; i < carsList.size(); i++) {
            assertEquals(carsList.get(i).license(), parkingModel.getCars().get(i).license());
        }
    }

    @Test
    @DisplayName("test if the removeCar method removes cars from the cars list")
    void parkingModel_removeCar_test() {
        for(String[] s : params) {
            ICar c = new Car(s);
            parkingModel.addCar(c);
            parkingModel.removeCar(c);
            assertFalse(parkingModel.getCars().contains(c));
        }
        assertEquals(0, parkingModel.getCars().size());
    }

    @Test
    @DisplayName("tests if the removedCar list has every car that has been removed")
    void parkingModel_removedCarList_test() {
        for(String[] s : params) {
            ICar c = new Car(s);
            parkingModel.addCar(c);
            parkingModel.removeCar(c);
        }
        assertEquals(params.size(), parkingModel.getRemovedCars().size());
        for(int i = 0; i < carsList.size(); i++) {
            assertEquals(carsList.get(i).license(), parkingModel.getRemovedCars().get(i).license());
        }
    }

    @Test
    @DisplayName("tests if the car is in no list after been deleted")
    void parkingModel_deleteCar_test() {
        for(String[] s : params) {
            ICar c = new Car(s);
            parkingModel.addCar(c);
            parkingModel.deleteCar(c);
        }
        assertEquals(0, parkingModel.getCars().size());
        assertEquals(0, parkingModel.getRemovedCars().size());
        assertEquals(0, parkingModel.getAllCars().size());
    }

    @Test
    @DisplayName("test if the deleteCar method throws a IllegalArgumentException if the car is not in the list")
    void parkingModel_deleteCarThrow_test() {
        assertThrows(IllegalArgumentException.class, () -> parkingModel.deleteCar(carsList.get(0)));
    }

    @Test
    @DisplayName("test if the addCarRestartServer method adds the right cars to the list")
    void parkingModel_addCarRestartServer_test() {
        for(String[] s : params) {
            parkingModel.addCarRestartServer(new Car(s));
        }
        assertEquals(params.size(), parkingModel.getCars().size());
        for(int i = 0; i < carsList.size(); i++) {
            assertEquals(carsList.get(i).license(), parkingModel.getCars().get(i).license());
        }
    }

    @Test
    @DisplayName("")
    void parkingModel_removeCarRestartServer_test() {
        for(String[] s : params) {
            parkingModel.removeCarRestartServer(new Car(s));
        }
        assertEquals(params.size(), parkingModel.getRemovedCars().size());
        for(int i = 0; i < carsList.size(); i++) {
            assertEquals(carsList.get(i).license(), parkingModel.getRemovedCars().get(i).license());
        }
    }

    @Test
    @DisplayName("test if the dailyEarnings adds up the cars that left in the last 24h")
    void parkingModel_dailyEarnings_test() {
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals(69, parkingModel.dailyEarnings());
        x = new Car(new String[]{"25", Time.now() - Time.MILLISECONDS_PER_DAY - 6011+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - Time.MILLISECONDS_PER_DAY - 6011+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals(69, parkingModel.dailyEarnings());
    }

    @Test
    @DisplayName("test if the weeklyEarnings adds up the cars that left in the last 7 days")
    void parkingModel_weeklyEarnings_test() {
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals(69, parkingModel.weeklyEarnings());
        x = new Car(new String[]{"25", Time.now() - Time.MILLISECONDS_PER_WEEK - 6011+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - Time.MILLISECONDS_PER_WEEK - 6011+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals(69, parkingModel.weeklyEarnings());
    }

    @RepeatedTest(10)
    @DisplayName("test if the dif between the price of the and the currentCost is less then 5 because of the delay")
    void parkingModel_currentCost_test() {
        for(ICar car : carsNoDurationList) {
            parkingModel.addCar(car);
            assertTrue(Math.abs(car.price() - parkingModel.currentCost().get(car.license())) <= 4);
        }

    }

    @Test
    @DisplayName("test if getAllCars returns a list with added and removed cars")
    void parkingModel_getCars_test() {
        for(String[] s : params) {
            ICar c = new Car(s);
            parkingModel.addCar(c);
            if(c.nr() % 2 == 0) parkingModel.removeCar(c);
        }
        assertEquals(params.size(), parkingModel.getAllCars().size());
    }
}
