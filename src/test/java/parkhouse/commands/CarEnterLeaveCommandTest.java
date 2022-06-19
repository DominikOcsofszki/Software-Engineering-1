package parkhouse.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import parkhouse.Data;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

import java.util.ArrayList;
import java.util.List;

public class CarEnterLeaveCommandTest {

    private final List<String[]> params = Data.paramsDuration();
    private  List<ICar> carsList = new ArrayList<>();
    private IParkingController controller;
    private Commander commander;

    @BeforeEach
    void setUp() {
        controller = new ParkingController();
        commander = new Commander();
        for(String[] s : params) {
            carsList.add(new Car(s));
        }
    }

    @AfterEach
    void tearDown() {
        controller = null;
        carsList = null;
        commander = null;
    }

    @Test
    @DisplayName("CarEnterCommand/Test if the car is in the list using activate()")
    void carEnterCommand_executeCarIsInList_test() {
        for(int i = 0; i < carsList.size(); i++) {
            commander.queue(new CarEnterCommand(carsList.get(i), controller));
            commander.activate();
            assertEquals(i + 1, controller.getCars().size());
        }
    }

    @Test
    @DisplayName("CarEnterCommand/Test if the car is not in the list using undo()")
    void carEnterCommand_undoCarIsNotInList_test() {
        for(int i = 0; i < carsList.size(); i++) {
            commander.queue(new CarEnterCommand(carsList.get(i), controller));
            commander.activate();
            assertEquals(i + 1, controller.getCars().size());
        }
        for(int i = 0; i < carsList.size(); i++) {
            commander.undo();
            assertEquals(carsList.size() - 1 - i, controller.getCars().size());
        }
    }

    @Test
    @DisplayName("CarEnterCommand/Test if undo throws IllegalArgumentException if the car is not in the list")
    void carEnterCommand_undoWithoutExecute_test() {
        assertThrows(IllegalArgumentException.class, commander::undo);
    }

    @Test
    @DisplayName("CarLeaveCommand/Test if the car is in the list using activate()")
    void carLeaveCommand_executeCarIsInList_test() {
        for(ICar car : carsList) {
            controller.addCar(car);
        }
        for(int i = 0; i < carsList.size(); i++) {
            commander.queue(new CarLeaveCommand(carsList.get(i), controller));
            commander.activate();
            assertEquals(carsList.size() - 1 - i, controller.getCars().size());
            assertEquals(i + 1, controller.getRemovedCars().size());
        }
    }

    @Test
    @DisplayName("CarLeaveCommand/Test if the car is not in the list using undo()")
    void carLeaveCommand_undoCarIsNotInList_test() {
        for(ICar car : carsList) {
            controller.addCar(car);
        }
        for (ICar iCar : carsList) {
            commander.queue(new CarLeaveCommand(iCar, controller));
            commander.activate();
        }
        for(int i = 0; i < carsList.size(); i++) {
            commander.undo();
            assertEquals(i + 1, controller.getCars().size());
            assertEquals(carsList.size() - 1 - i, controller.getRemovedCars().size());
        }
    }

    @Test
    @DisplayName("CarLeaveCommand/Test if undo throws IllegalArgumentException if the car is not in the list")
    void carLeaveCommand_undoWithoutExecute_test() {
        assertThrows(IllegalArgumentException.class, commander::undo);
    }
}