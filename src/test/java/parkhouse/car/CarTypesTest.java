package parkhouse.car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import parkhouse.car.CarTypes;

public class CarTypesTest {

    CarTypes pkw;
    CarTypes quad;
    CarTypes trike;
    CarTypes pickup;

    @BeforeEach
    void setUp(){
        pkw = CarTypes.getInstance("pkw");
        quad = CarTypes.getInstance("quad");
        trike = CarTypes.getInstance("trike");
        pickup = CarTypes.getInstance("pickup");
    }

    @Test
    @DisplayName("getInstance: returns the instance back")
    void getInstace_returnsSameInstance_instanceIsTheSame(){
        Assertions.assertEquals(pkw, CarTypes.getInstance("pkw"));
        Assertions.assertEquals(quad, CarTypes.getInstance("quad"));
        Assertions.assertEquals(trike, CarTypes.getInstance("trike"));
        Assertions.assertEquals(pickup, CarTypes.getInstance("pickup"));
    }


}