package parkhouse.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import parkhouse.Data;
import parkhouse.calculations.Locator;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;

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
    void carEnterCommand_executeAndUnexecute_test() {
        int spaceNr = Locator.locate(controller);
        ICommand command = new CarEnterCommand(car, spaceNr, controller);
        command.execute();
        assertEquals(1, controller.getCars().size());
        command.undo();
        assertEquals(0, controller.getCars().size());
    }

}
