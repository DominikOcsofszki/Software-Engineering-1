package parkhouse.util;

import org.junit.jupiter.api.Test;
import parkhouse.car.Car;
import parkhouse.car.CarIF;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FinderTest {

    Car c1 = new Car(new String[]{"1","1650969214942","5","6","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","1","Frau","PKW","SU-S 8","16509697749492"});
    Car c2 = new Car(new String[]{"6","1650969215214","8","9","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-A 3","16509697749492"});
    List<CarIF> cars = Arrays.asList(c1, c2);

    @Test
    public void findCarByNrTest() {
        assertEquals(c1, Finder.findCar(cars, CarIF::nr, 1));
    }

    @Test
    public void findCarByLicenseTest() {
        assertEquals(c2, Finder.findCar(cars, CarIF::license, "SU-A 3"));
    }

    @Test
    public void findExceptionTest() {
        assertThrows(NoSuchElementException.class, () -> Finder.findCar(cars, CarIF::nr, 2));
    }
}
