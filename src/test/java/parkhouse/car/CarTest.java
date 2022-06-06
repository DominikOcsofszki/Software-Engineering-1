package parkhouse.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    List<String[]> params = Data.params();

    @Test
    @DisplayName("Test if 'nr' is set correctly")
    void car_nr_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Integer.parseInt(p[0]), car.nr());
        }
    }

    @Test
    @DisplayName("Test if 'begin' is set correctly")
    void car_begin_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Long.parseLong(p[1]), car.begin());
        }
    }

    @Test
    @DisplayName("Test if 'duration' is set correctly")
    void car_duration_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            if (p[2].equals("_")) {
                assertEquals(0, car.duration());
            } else {
                assertEquals(Integer.parseInt(p[2]), car.duration());
            }
        }
    }

    @Test
    @DisplayName("Test if 'price' is set correctly")
    void car_price_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            if (p[3].equals("_")) {
                assertEquals(0, car.duration());
            } else {
                assertEquals(Double.parseDouble(p[3]), car.price());
            }
        }
    }

    @Test
    @DisplayName("Test if 'ticket' is set correctly")
    void car_ticket_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(p[4], car.ticket());
        }
    }

    @Test
    @DisplayName("Test if 'color' is set correctly")
    void car_color_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(p[5], car.color());
        }
    }

    @Test
    @DisplayName("Test if 'space' is set correctly")
    void car_space_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Integer.parseInt(p[6]), car.space());
        }
    }

    @Test
    @DisplayName("Test if 'category' is set correctly")
    void car_category_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(p[7], car.category());
        }
    }

    @Test
    @DisplayName("Test if 'type' is set correctly")
    void car_type_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(p[8], car.type());
        }
    }

    @Test
    @DisplayName("Test if 'license' is set correctly")
    void car_license_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(p[9], car.license());
        }
    }

    @Test
    @DisplayName("Test if 'end' is calculated correctly")
    void car_end_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(car.begin() + car.duration(), car.end());
        }
    }

    @Test
    @DisplayName("Test if parameters are updated correctly")
    void car_toString_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Arrays.toString(p), car.toString());
        }
    }

    @Test
    @DisplayName("Test if parameters are updated correctly")
    void car_updateParams_test() {
        ICar car = new Car(new String[]{"_","_","_","_","_","_","_","_","_","_"});
        for (String[] p : params) {
            car.updateParams(p);
            assertEquals(Arrays.toString(p), car.toString());
        }
    }

    @Test
    @DisplayName("Test if space is set correctly")
    void car_setSpace_test() {
        Random rand = new Random();
        for (String[] p : params) {
            ICar car = new Car(p);
            int space = rand.nextInt();
            car.setSpace(space);
            assertEquals(space, car.space());
        }
    }
}