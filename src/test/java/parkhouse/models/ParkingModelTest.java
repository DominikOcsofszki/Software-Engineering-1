package parkhouse.models;

import org.junit.jupiter.api.*;
import parkhouse.Data;
import parkhouse.calculations.Price;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.views.DailyEarningsView;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingModelTest {

    ParkingModel parkingModel;
    List<String[]> params = Data.params();
    String[] leave= new String[]{"0", "" + System.nanoTime() / 1000000, "_", "1000", "", "", "", "", "", "", "", "", ""};
    Car c;
    Car leaveCar;

    @BeforeEach
    void setUp() {
        parkingModel = new ParkingModel();
        c = new Car(params.get(0));
        leaveCar = new Car(leave);
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
    }

    @Test
    void parkingModel_addCar_test() {
        parkingModel.addCar(c);
        assertEquals(params.get(0)[9], parkingModel.getCars().get(0).license());
        assertEquals(1, parkingModel.getCars().size());
    }

    @Test
    void parkingModel_removeCar_test() {
        parkingModel.addCar(c);
        assertEquals(1, parkingModel.getCars().size());
        parkingModel.removeCar(c);
        assertEquals(0, parkingModel.getCars().size());
    }

    @Test
    void parkingModel_dailyEarnings_test() {
        parkingModel.addCar(leaveCar);
        parkingModel.removeCar(leaveCar);
        assertEquals(10, parkingModel.dailyEarnings());
    }

    @Test
    void parkingModel_weeklyEarnings_test() {
        parkingModel.addCar(leaveCar);
        parkingModel.removeCar(leaveCar);
        assertEquals(10, parkingModel.weeklyEarnings());
    }

    @RepeatedTest(10)
//    @Test
    void parkingModel_currentCost_test() {
        parkingModel.addCar(leaveCar);
//        System.out.println(Price.price(leaveCar));
        assertEquals(Price.price(leaveCar), parkingModel.currentCost().get(leaveCar.license()),0.5);    //ToDo Why still so many fails?
    }
    @RepeatedTest(10)
//    @Test
    void parkingModel_currentCost_test_1() {
        parkingModel.addCar(leaveCar);
//        System.out.println(Price.price(leaveCar));
        assertEquals(Price.price(leaveCar), parkingModel.currentCost().get(leaveCar.license()),1.);
    }

    @Test
    void parkingModel_getRemovedCars_test() {
        parkingModel.addCar(c);
        parkingModel.removeCar(c);
        assertEquals(c, parkingModel.getRemovedCars().get(0));
    }

    @Test
    void parkingModel_registerObserver_Test() {
        DailyEarningsView observer = new DailyEarningsView(parkingModel);
        parkingModel.removeObserver(observer);
        parkingModel.registerObserver(observer);
        parkingModel.addCar(leaveCar);
        parkingModel.removeCar(leaveCar);
        assertEquals(10, observer.getDailyEarnings());
    }

    @Test
    void parkingModel_removeObserver_Test() {
        DailyEarningsView observer = new DailyEarningsView(parkingModel);
        parkingModel.removeObserver(observer);
        parkingModel.addCar(leaveCar);
        parkingModel.removeCar(leaveCar);
        assertEquals(0, observer.getDailyEarnings());
    }

    @Test
    void parkingModel_getCarsAndRemovedCars() {
        parkingModel.addCar(leaveCar);
        parkingModel.removeCar(leaveCar);
        parkingModel.addCar(c);
        List<ICar> list = parkingModel.getCarsAndRemovedCars();
        assertEquals(2, list.size());
        assertEquals(leaveCar, list.get(1));
        assertEquals(c, list.get(0));
    }

}
