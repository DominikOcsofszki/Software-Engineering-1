package parkhouse.util;

import parkhouse.car.CarIF;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.List;

public class Jsonify {

    public static JsonArray carsAsNr(List<CarIF> cars) {
        JsonArrayBuilder nrArray = Json.createArrayBuilder();
        for (CarIF c : cars) {
            nrArray.add(Json.createValue(c.nr()));
        }
        return nrArray.build();
    }

    public static JsonArray carsAsDuration(List<CarIF> cars) {
        JsonArrayBuilder durationArray = Json.createArrayBuilder();
        for (CarIF c : cars) {
            durationArray.add(Json.createValue(c.duration()));
        }
        return durationArray.build();
    }
}
