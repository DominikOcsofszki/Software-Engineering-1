package parkhouse.util;

import org.junit.jupiter.api.Test;
import parkhouse.car.Car;
import parkhouse.car.ICar;

import javax.json.JsonArray;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonifyTest {

    Car c1 = new Car(new String[]{"1", "1650969214942", "5", "6", "9fbb53684b77f16f9e88faa9e7d63d2b", "#0c0f15", "1", "Frau", "PKW", "SU-S 8", "16509697749492"});
    Car c2 = new Car(new String[]{"6","1650969215214","8","9","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"});

    @Test
    void asNrArrayTest() {
        List<ICar> cars = Arrays.asList(c1, c2);
        JsonArray nr = Jsonify.carsAsNr(cars);
        assertEquals("1", nr.get(0).toString());
        assertEquals("6", nr.get(1).toString());
    }

    @Test
    void asDurationArrayTest() {
        List<ICar> cars = Arrays.asList(c1, c2);
        JsonArray nr = Jsonify.carsAsDuration(cars);
        assertEquals("5", nr.get(0).toString());
        assertEquals("8", nr.get(1).toString());
    }
}
