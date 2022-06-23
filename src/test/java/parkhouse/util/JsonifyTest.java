package parkhouse.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.config.Config;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonifyTest {

    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if json array contains the correct values")
    void jsonify_carsAsJsonArray_test() {
        List<Function<ICar,Object>> func = Arrays.asList(ICar::ticket, ICar::license, ICar::begin);
        for (Function<ICar,Object> f : func) {
            JsonArray arr = Jsonify.carsAsJsonArray(cars, f);
            for (int i = 0; i < cars.size(); i++) {
                assertEquals(f.apply(cars.get(i)).toString(), arr.getString(i));
            }
        }
    }

    @Test
    @DisplayName("Test if car property is counted correctly")
    void jsonify_carsCount_test() {
        JsonObject count = Jsonify.carsCount(cars, ICar::type);
        JsonArray keys = Jsonify.getKeys(count);
        JsonArray values = Jsonify.getValues(count);
        for (String t : Config.VEHICLE_TYPES) {
            assertTrue(keys.contains(t));
        }
    }

    @ParameterizedTest
    @DisplayName("Test if correct plot object is build")
    @CsvSource({"bar,BarPlot","line,LinePlot","pie,PiePlot"})
    void jsonify_plot_test(String type, String name) {
        JsonArray duration = Jsonify.carsAsJsonArray(cars, ICar::duration);
        JsonObject plot = Jsonify.plot(Jsonify.carsAsJsonArray(cars, ICar::nr), duration, type, name);
        JsonObject data = (JsonObject) plot.getJsonArray("data").get(0);
        assertEquals(Jsonify.carsAsJsonArray(cars, ICar::nr), data.getJsonArray("x"));
        assertEquals(duration, data.getJsonArray("y"));
        assertEquals(type, data.getString("type"));
        assertEquals(name, data.getString("name"));
    }

}
