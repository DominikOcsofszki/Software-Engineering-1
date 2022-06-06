package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import parkhouse.Data;
import parkhouse.car.ICar;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonifyTest {

    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if json array contains the correct nr")
    void jsonify_asNrArray_test() {
        JsonArray nr = Jsonify.carsAsNr(cars);
        for (int i = 0; i < cars.size(); i++) {
            assertEquals(cars.get(i).nr(), nr.getInt(i));
        }
    }

    @Test
    @DisplayName("Test if json array contains the correct duration")
    void jsonify_asDurationArray_test() {
        JsonArray dr = Jsonify.carsAsDuration(cars);
        for (int i = 0; i < cars.size(); i++) {
            assertEquals(cars.get(i).duration(), dr.getInt(i));
        }
    }

    @ParameterizedTest
    @DisplayName("Test if correct plot object is build")
    @CsvSource({"bar,BarPlot","line,LinePlot","pie,PiePlot"})
    void jsonify_plot_test(String type, String name) {
        JsonObject plot = Jsonify.plot(Jsonify.carsAsNr(cars), Jsonify.carsAsDuration(cars), type, name);
        JsonObject data = (JsonObject) plot.getJsonArray("data").get(0);
        assertEquals(Jsonify.carsAsNr(cars), data.getJsonArray("x"));
        assertEquals(Jsonify.carsAsDuration(cars), data.getJsonArray("y"));
        assertEquals(type, data.getString("type"));
        assertEquals(name, data.getString("name"));
    }

}
