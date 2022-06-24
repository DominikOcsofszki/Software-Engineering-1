package parkhouse.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Jsonify;

import javax.json.Json;
import javax.json.JsonArray;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientCategoriesViewTest {

    List<ICar> cars;
    IParkingController controller;

    @BeforeEach
    public void setup() {
        cars = Data.cars();
        controller = new ParkingController();
        for (ICar c : cars) {
            if (c.gone()) {
                controller.addRemovedCarRestartServer(c);
            } else {
                controller.addCar(c);
            }
        }
    }

    @Test
    @DisplayName("Test if view generates correct graph")
    public void clientCategoriesView_toString_test() {
        JsonArray keys = Json.createArrayBuilder()
                .add("Women")
                .add("Family")
                .add("Default")
                .add("Business")
                .build();
        JsonArray values = Json.createArrayBuilder()
                .add(2)
                .add(4)
                .add(1)
                .add(2)
                .build();
        assertEquals(
                Jsonify.plot(keys, values, "bar", "Categories").toString(),
                controller.clientCategoriesView().toString()
        );
    }

}
