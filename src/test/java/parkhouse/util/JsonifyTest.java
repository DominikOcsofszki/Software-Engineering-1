package parkhouse.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parkhouse.car.Car;
import parkhouse.car.ICar;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonifyTest {

    Car c1 = new Car(new String[]{"1", "1650969214942", "5", "6", "9fbb53684b77f16f9e88faa9e7d63d2b", "#0c0f15", "1", "Frau", "PKW", "SU-S 8", "16509697749492"});
    Car c2 = new Car(new String[]{"6","1650969215214","8","9","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"});

    List<ICar> cars;

    @BeforeEach
    void setup() {
        cars = Arrays.asList(c1, c2);
    }

    @Test
    void asNrArrayTest() {
        JsonArray nr = Jsonify.carsAsNr(cars);
        assertEquals("1", nr.get(0).toString());
        assertEquals("6", nr.get(1).toString());
    }

    @Test
    void asDurationArrayTest() {
        JsonArray dr = Jsonify.carsAsDuration(cars);
        assertEquals("5", dr.get(0).toString());
        assertEquals("8", dr.get(1).toString());
    }

    @Test
    void plotTest() {
        JsonObject plot = Jsonify.plot(Jsonify.carsAsNr(cars), Jsonify.carsAsDuration(cars), "bar", "Test");
        JsonObject data = (JsonObject) plot.getJsonArray("data").get(0);
        assertEquals(Jsonify.carsAsNr(cars), data.getJsonArray("x"));
        assertEquals(Jsonify.carsAsDuration(cars), data.getJsonArray("y"));
        assertEquals("bar", data.getString("type"));
        assertEquals("Test", data.getString("name"));
    }
}
