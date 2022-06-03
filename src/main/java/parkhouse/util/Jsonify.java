package parkhouse.util;

import parkhouse.car.ICar;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class Jsonify {

    public static JsonArray carsAsNr(List<ICar> cars) {
        JsonArrayBuilder nrArray = Json.createArrayBuilder();
        for (ICar c : cars) {
            nrArray.add(Json.createValue(c.nr()));
        }
        return nrArray.build();
    }

    public static JsonArray carsAsDuration(List<ICar> cars) {
        JsonArrayBuilder durationArray = Json.createArrayBuilder();
        for (ICar c : cars) {
            durationArray.add(Json.createValue(c.duration()));
        }
        return durationArray.build();
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

    public static JsonObject table(JsonArray headers, JsonArray data) {
        JsonArrayBuilder width = Json.createArrayBuilder();
        JsonArrayBuilder order = Json.createArrayBuilder();
        for (int i = 0; i < headers.size(); i++) {
            width.add(200);
            order.add(i);
        }
        return Json.createObjectBuilder()
                .add("columnwidth", width.build())
                .add("columnorder", order.build())
                .add("header", Json.createObjectBuilder()
                        .add("values", headers)
                        .add("align", "center")
                        .add("line", Json.createObjectBuilder()
                                .add("width", 1)
                                .add("color", "black")
                                .build()
                        )
                        .add("fill", Json.createObjectBuilder()
                                .add("color", "white")
                                .build()
                        )
                        .add("font", Json.createObjectBuilder()
                                .add("family", "Arial")
                                .add("size", 12)
                                .add("color", "black")
                                .build()
                        )
                        .build()
                )
                .add("cells", Json.createObjectBuilder()
                                .add("values", data)
                                .add("align", "center")
                                .add("line", Json.createObjectBuilder()
                                        .add("width", 1)
                                        .add("color", "black")
                                        .build()
                                )
                                .add("fill", Json.createObjectBuilder()
                                        .add("color", "white")
                                        .build()
                                )
                                .add("font", Json.createObjectBuilder()
                                        .add("family", "Arial")
                                        .add("size", 10)
                                        .add("color", "black")
                                        .build()
                                )
                                .build()
                        )
                .build();
    }

}
