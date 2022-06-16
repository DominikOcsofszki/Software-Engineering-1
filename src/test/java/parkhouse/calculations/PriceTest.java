package parkhouse.calculations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.Data;
import parkhouse.car.ICar;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceTest {

    List<ICar> cars = Data.cars();

    @Test
    @DisplayName("Test if price string is formatted correctly")
    public void price_format_test() {
        for (ICar c : cars) {
            assertEquals(
                    String.format(Locale.US, "%.2f€", c.price() / 100d),
                    Price.format(c.price())
            );
        }
    }

    @Test
    @DisplayName("Test if price factor is parsed correctly")
    public void price_priceFactor_test() {
        // TODO Find effective and dynamic way of testing price factors
    }

}
