package parkhouse.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.calculations.Price;
import parkhouse.config.Config;
import parkhouse.security.SanitizedCar;
import parkhouse.util.Time;

import java.security.SecureRandom;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    List<String[]> params = Data.params();
    SecureRandom rand = new SecureRandom();

    @Test
    @DisplayName("Test if 'nr' is set correctly")
    void car_nr_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Integer.parseInt(p[0]), car.nr());
        }
    }

    @Test
    @DisplayName("Test if 'timer' is set correctly")
    void car_timer_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Long.parseLong(p[1]), car.timer());
        }
    }

    @Test
    @DisplayName("Test if 'begin' is set correctly")
    void car_begin_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(Long.parseLong(p[10]), car.begin());
        }
    }

    @Test
    @DisplayName("Test if 'duration' is set correctly")
    void car_duration_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            if (p[2].equals("_")) {
                long duration = Math.max(Config.SIMULATION_SPEED, 1) * (Time.now() - car.timer());
                assertEquals(duration, car.duration(), 500);
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
                long price = Math.round(Price.priceFactor(car) * car.duration() / Math.max(Config.SIMULATION_SPEED, 1));
                assertEquals(price, car.price(), 50);
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
            assertEquals(car.begin() + car.duration(), car.end(), 500);
        }
    }

    @Test
    @DisplayName("Test if parameters are updated correctly")
    void car_toString_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            assertEquals(
                    String.format("%s/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s",
                            p[0], p[1], p[2], p[3], p[4], p[5],
                            p[6], p[7], p[8], p[9], p[10]
                            ), car.toString()
            );
        }
    }

    @Test
    @DisplayName("Test if car is recognized as 'gone' correctly")
    void car_gone_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            if (!p[2].equals("_")) {
                assertTrue(car.gone());
            } else if (!p[3].equals("_")) {
                assertTrue(car.gone());
            } else {
                assertFalse(car.gone());
            }
        }
    }

    @Test
    @DisplayName("Test if parameters are updated correctly")
    void car_updateParams_test() {
        ICar car = new Car(new String[]{"_","_","_","_","_","_","_","_","_","_"});
        for (String[] p : params) {
            car.updateParams(p);
            assertEquals(
                    String.format("%s/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s",
                            p[0], p[1], p[2], p[3], p[4], p[5],
                            p[6], p[7], p[8], p[9], p[10]
                    ), car.toString()
            );
        }
    }

    @Test
    @DisplayName("Test if space is set correctly")
    void car_setSpace_test() {
        for (String[] p : params) {
            ICar car = new Car(p);
            int space = rand.nextInt();
            car.setSpace(space);
            assertEquals(space, car.space());
        }
    }

    @Test
    @DisplayName("Test the car decorator")
    void car_decorator_test() {
        ICar empty = new SanitizedCar(new Car(new String[]{"_","_","_","_","_","_","_","_","_","_"}));
        for (String[] p : params) {
            ICar car = new SanitizedCar(new Car(p));

            int space = rand.nextInt();
            car.setSpace(space);
            assertEquals(space, car.space());

            empty.updateParams(p);
            assertEquals(
                    String.format("%s/%s/%s/%s/%s/%s/%s/%s/%s/%s/%s",
                            p[0], p[1], p[2], p[3], p[4], p[5],
                            p[6], p[7], p[8], p[9], p[10]
                    ), empty.toString()
            );
        }
    }
}