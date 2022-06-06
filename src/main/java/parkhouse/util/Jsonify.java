package parkhouse.util;

import parkhouse.car.ICar;

import javax.json.*;
import java.util.*;
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

    public static JsonObject carsCount(List<ICar> cars, Function<ICar,Object> func) {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        HashMap<Object,Integer> count = new HashMap<>();
        for (ICar c : cars) {
            if (!count.containsKey(func.apply(c))) {
                count.put(func.apply(c), 1);
            } else {
                count.put(func.apply(c), count.get(func.apply(c)) + 1);
            }
        }
        for (Map.Entry<Object,Integer> e : count.entrySet()) {
            obj.add(e.getKey().toString(), e.getValue());
        }
        return obj.build();
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

    public static JsonArray getKeys(JsonObject obj) {
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for (String k : obj.keySet()) {
            arr.add(k);
        }
        return arr.build();
    }

    public static JsonArray getValues(JsonObject obj) {
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for (Map.Entry<String,JsonValue> v : obj.entrySet()) {
            arr.add(v.getValue());
        }
        return arr.build();
    }

}
