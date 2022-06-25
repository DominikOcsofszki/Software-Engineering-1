package parkhouse.controller;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.commands.CarEnterCommand;
import parkhouse.util.Time;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingControllerTest {

    private IParkingController parkingController;
    private final List<String[]> param = Data.paramsDuration();
    private List<ICar> carList = new ArrayList<>();
    private final ICar carNow = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
            "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
    private final ICar carYesterday = new Car(new String[]{"25", Time.now() - Time.MILLISECONDS_PER_DAY - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
            "#42671f","2","Women","SUV","SU-K 41",Time.now() - Time.MILLISECONDS_PER_DAY - 10000+""});
    private final ICar carLastWeek = new Car(new String[]{"25", Time.now() - Time.MILLISECONDS_PER_WEEK - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
            "#42671f","2","Women","SUV","SU-K 41",Time.now() - Time.MILLISECONDS_PER_WEEK - 10000+""});

    @BeforeEach
    void setUp() {
        parkingController = new ParkingController();
        for(String[] s : param) {
            carList.add(new Car(s));
        }
    }

    @AfterEach
    void tearDown() {
        parkingController = null;
        carList = null;
    }

    @Test
    @DisplayName("test if the added Car is in the getCars list")
    void parkingController_addCar_test() {
        for(int i = 0; i < carList.size(); i++) {
            parkingController.addCar(carList.get(i));
            assertEquals(i + 1, parkingController.getCars().size());
        }
    }

    @Test
    @DisplayName("test if the removed car is in the getRemovedCars list")
    void parkingController_removeCar_test() {
        for (ICar iCar : carList) {
            parkingController.addCar(iCar);
        }
        assertEquals(param.size(), parkingController.getCars().size());
        for(int i = 0; i < carList.size(); i++) {
            parkingController.removeCar(carList.get(i));
            assertEquals(i + 1, parkingController.getRemovedCars().size());
            assertEquals(carList.size() - 1 - i, parkingController.getCars().size());
        }
    }

    @Test
    @DisplayName("tests if the deleted car is in now list")
    void parkingController_deleteCar_test() {
        for(int i = 0; i < carList.size(); i++) {
            parkingController.addCar(carList.get(i));
            if(i % 2 == 0) parkingController.removeCar(carList.get(i));
            assertEquals(i + 1, parkingController.getAllCars().size());
        }
        for(int i = 0; i < carList.size(); i++) {
            parkingController.deleteCar(carList.get(i));
            assertEquals(carList.size() - i - 1, parkingController.getAllCars().size());
        }
    }

    @Test
    @DisplayName("tests if the add car is in the list")
    void parkingCOntroller_addCarRestartServer_test() {
        for(int i = 0; i < carList.size(); i++) {
            parkingController.addCarRestartServer(carList.get(i));
            assertEquals(i + 1, parkingController.getCars().size());
        }
    }

    @Test
    @DisplayName("tests if the removed car is in the list")
    void ParkingController_removeCarRestartServer_test() {
        for(int i = 0; i < carList.size(); i++) {
            parkingController.addRemovedCarRestartServer(carList.get(i));
            assertEquals(i + 1, parkingController.getRemovedCars().size());
        }
    }

    @Test
    @DisplayName("")
    void parkingController_dailyEarningsView_test() {
        parkingController.addCar(carNow);
        parkingController.removeCar(carNow);
        assertEquals(69, parkingController.dailyEarningsView().getDailyEarnings());
        parkingController.addCar(carYesterday);
        parkingController.removeCar(carYesterday);
        assertEquals(69, parkingController.dailyEarningsView().getDailyEarnings());
    }

    @Test
    @DisplayName("")
    void parkingController_weeklyEarningsView_test() {
        parkingController.addCar(carNow);
        parkingController.removeCar(carNow);
        assertEquals(69, parkingController.weeklyEarningsView().getWeeklyEarnings());
        parkingController.addCar(carLastWeek);
        parkingController.removeCar(carLastWeek);
        assertEquals(69, parkingController.weeklyEarningsView().getWeeklyEarnings());
    }

    @Test
    @DisplayName("")
    void parkingController_currentCostView_test() {
        for(ICar car : carList) {
            parkingController.addCar(car);
            assertEquals(car.price(), parkingController.currentCostView().getCurrentCosts().get(car.license()));
        }
    }

    @Test
    @DisplayName("")
    void parkingController_clientCategoriesView_test() {
        //TODO
    }

    @Test
    @DisplayName("")
    void parkingController_vehicleTypesView_test() {
        //TODO
    }

    @Test
    @DisplayName("")
    void parkingController_commander_test() {
        for(ICar car : carList) {
            parkingController.commander().queue(new CarEnterCommand(car, parkingController));
            parkingController.commander().activate();
        }
        assertEquals(carList.size(), parkingController.getCars().size());
    }

}
