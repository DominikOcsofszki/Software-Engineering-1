package parkhouse.util;

import parkhouse.car.ICar;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;
import java.util.function.Function;

public class Jsonify {

    private Jsonify() {}

    // obsolete
    public static JsonArray carsAsNr(List<ICar> cars) {
        JsonArrayBuilder nrArray = Json.createArrayBuilder();
        for (ICar c : cars) {
            nrArray.add(Json.createValue(c.nr()));
        }
        return nrArray.build();
    }

    // obsolete
    public static JsonArray carsAsDuration(List<ICar> cars) {
        JsonArrayBuilder durationArray = Json.createArrayBuilder();
        for (ICar c : cars) {
            durationArray.add(Json.createValue(c.duration()));
        }
        return durationArray.build();
    }

    public static JsonArray carsAsJsonArray(List<ICar> cars, Function<ICar,Object> func) {
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for (ICar c : cars) {
            arr.add(Json.createValue(func.apply(c).toString()));
        }
        return arr.build();
    }

    public static JsonObject plot(JsonArray x, JsonArray y, String type, String name) {
        return Json.createObjectBuilder()
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("x", x)
                                .add("y", y)
                                .add("type", type)
                                .add("name", name)
                        )).build();
    }

}
