package parkhouse.stats;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import parkhouse.Data;
import parkhouse.calculations.StatsRemovedSum;
import parkhouse.car.Car;
import parkhouse.controller.ParkingController;
import parkhouse.models.ParkingModel;
import parkhouse.util.Time;

import java.util.List;

public class AbstractStatsTest {

    ParkingModel parkingModel;
    List<String[]> params = Data.params();
    String[] leave= Data.params().get(0);

    Car c;
    Car leaveCar;

    @BeforeEach
    void setUp() {
        leave[2] = Time.simNow()+"";
        parkingModel = new ParkingModel();
        c = new Car(params.get(0));
        leaveCar = new Car(leave);
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
    }


    @Test
    void statsSumtest() {
        ParkingController controller = new ParkingController();
        Car leaveCar = new Car(new String[]{"0", "" + System.nanoTime() / 1000000, "", "1000", "", "", "", "", "", "", "", "", ""});
        controller.parkingModel().addCar(leaveCar);     //ToDo use real cars from the csv?
        controller.parkingModel().removeCar(leaveCar);
        assertEquals(10, new StatsRemovedSum(controller).template1());
    }

    @Test
    void statsAvgtest() {
        ParkingController controller = new ParkingController();
        Car leaveCar = new Car(new String[]{"0", "" + System.nanoTime() / 1000000, "", "1000", "", "", "", "", "", "", "", "", ""});
        controller.parkingModel().addCar(leaveCar);     //ToDo use real cars from the csv?
        controller.parkingModel().removeCar(leaveCar);
        assertEquals(10, new StatsRemovedSum(controller).template1());
    }

}