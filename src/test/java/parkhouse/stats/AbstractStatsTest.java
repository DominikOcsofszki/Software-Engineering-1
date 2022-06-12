package parkhouse.stats;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import parkhouse.calculations.StatsAvg;
import parkhouse.calculations.StatsSum;
import parkhouse.car.Car;
import parkhouse.controller.ParkingController;

public class AbstractStatsTest {

    @Test
    void statsSumtest() {
        ParkingController controller = new ParkingController();
        Car leaveCar = new Car(new String[]{"0", "" + System.nanoTime() / 1000000, "", "1000", "", "", "", "", "", "", "", "", ""});
        controller.parkingModel().addCar(leaveCar);     //ToDo use real cars from the csv?
        controller.parkingModel().removeCar(leaveCar);
        assertEquals(10, new StatsSum().template1(controller));
    }

    @Test
    void statsAvgtest() {
        ParkingController controller = new ParkingController();
        Car leaveCar = new Car(new String[]{"0", "" + System.nanoTime() / 1000000, "", "1000", "", "", "", "", "", "", "", "", ""});
        controller.parkingModel().addCar(leaveCar);     //ToDo use real cars from the csv?
        controller.parkingModel().removeCar(leaveCar);
        assertEquals(10, new StatsAvg().template1(controller));
    }

}