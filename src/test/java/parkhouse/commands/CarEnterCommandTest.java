package parkhouse.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import parkhouse.Data;
import parkhouse.calculations.Locator;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Finder;

import java.util.ArrayList;
import java.util.List;

public class CarEnterCommandTest {

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
    @DisplayName("Test if the car is in the list using activate()")
    void carEnterCommand_executeCarIsInList_test() {
        for(int i = 0; i < carsList.size(); i++) {
            commander.queue(new CarEnterCommand(carsList.get(i), controller));
            commander.activate();
            assertEquals(i + 1, controller.getCars().size());
        }
    }

    @Test
    @DisplayName("Test if the car is not in the list using undo()")
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
    @DisplayName("Test if undo throws IllegalArgumentException if the car is not in the list")
    void carEnterCommand_undoWithoutExecute_test() {
        assertThrows(IllegalArgumentException.class, commander::undo);
    }
}
