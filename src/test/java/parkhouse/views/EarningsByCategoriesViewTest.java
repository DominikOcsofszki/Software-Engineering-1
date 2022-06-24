package parkhouse.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.calculations.Price;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Tableize;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarningsByCategoriesViewTest {

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
    @DisplayName("Test if view generates correct table")
    public void earningsByCategoriesView_toString_test() {
        String[] headers = new String[] {"Category", "Sum", "Average", "Minimum", "Maximum"};
        String[][] rows = new String[][] {
                {"Default", Price.format(5819), Price.format(2909), Price.format(601), Price.format(5218)},
                {"Women", Price.format(801), Price.format(801), Price.format(801), Price.format(801)},
                {"Business", Price.format(401), Price.format(401), Price.format(401), Price.format(401)},
                {"Family", Price.format(0), Price.format(0), Price.format(0), Price.format(0)},
                {"Disability", Price.format(3206), Price.format(1603), Price.format(400), Price.format(2806)}
        };
        assertEquals(Tableize.table(headers, rows), controller.earningsByCategoriesView().toString());
    }

}
