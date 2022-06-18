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

import java.util.List;

public class CarEnterCommandTest {

    List<String[]> params = Data.params();
    IParkingController controller = new ParkingController();
    ICar car;

    @BeforeEach
    void setUp() {
        car = new Car(params.get(0));
    }

    @AfterEach
    void tearDown() {
        car = null;
    }

    @Test
    @DisplayName("Test if the car is in the list using execute()")
    void carEnterCommand_executeCarIsInList_test() {
        ICommand command = new CarEnterCommand(car, controller);
        command.execute();
        assertEquals(1, controller.getCars().size());
    }

    @Test
    @DisplayName("Test if the car is not in the list using undo()")
    void carEnterCommand_undoCarIsNotInList_test() {
        ICommand command = new CarEnterCommand(car, controller);
        command.execute();
        assertEquals(1, controller.getCars().size());
        command.undo();
        assertEquals(0, controller.getCars().size());
    }

    @Test
    @DisplayName("Test if undo throws IllegalArgumentException if the car is not in the list")
    void carEnterCommand_undoWithoutExecute_test() {
        ICommand command = new CarEnterCommand(car, controller);
        assertThrows(IllegalArgumentException.class, command::undo);
    }
}
