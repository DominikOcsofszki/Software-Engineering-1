package parkhouse.car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CarTypesTest {

    /*
    Author: staher2s
     */

    private static final String PKW = "pkw";
    private static final String QUAD = "quad";
    private static final String TRIKE = "trike";
    private static final String PICKUP = "pickup";

    private CarTypes pkw;
    private CarTypes quad;
    private CarTypes trike;
    private CarTypes pickup;

    @BeforeEach
    void setup(){
        pkw = CarTypes.getInstance(PKW);
        quad = CarTypes.getInstance(QUAD);
        trike = CarTypes.getInstance(TRIKE);
        pickup = CarTypes.getInstance(PICKUP);
    }

    @Test
    @DisplayName("getInstance: returns the instance back")
    void getInstanceReturnsSameInstanceInstanceIsTheSame(){
        Assertions.assertEquals(pkw, CarTypes.getInstance(PKW));
        Assertions.assertEquals(quad, CarTypes.getInstance(QUAD));
        Assertions.assertEquals(trike, CarTypes.getInstance(TRIKE));
        Assertions.assertEquals(pickup, CarTypes.getInstance(PICKUP));

    }
    @Test
    @DisplayName("getInstance: not the same type")
    void getInstanceReturnsSameInstanceInstanceNotTheSame() {
        Assertions.assertNotEquals(pkw, CarTypes.getInstance(QUAD));
        Assertions.assertNotEquals(quad, CarTypes.getInstance(PKW));
        Assertions.assertNotEquals(trike, CarTypes.getInstance(PICKUP));
        Assertions.assertNotEquals(pickup, CarTypes.getInstance(TRIKE));

        Assertions.assertNotEquals(pkw, CarTypes.getInstance(PICKUP));
        Assertions.assertNotEquals(quad, CarTypes.getInstance(TRIKE));
        Assertions.assertNotEquals(trike, CarTypes.getInstance(PKW));
        Assertions.assertNotEquals(pickup, CarTypes.getInstance(QUAD));

        Assertions.assertNotEquals(pkw, CarTypes.getInstance(TRIKE));
        Assertions.assertNotEquals(quad, CarTypes.getInstance(PICKUP));
        Assertions.assertNotEquals(trike, CarTypes.getInstance(QUAD));
        Assertions.assertNotEquals(pickup, CarTypes.getInstance(PKW));

       }

    @Test
    @DisplayName("get/setFactor:sets and returns expectet value ")
    void getFactorReturnsExpectetValueIsTheSame(){
        pkw.setFactor(2.0);
        Assertions.assertEquals(pkw.getFactor(),2.0);
    }
    @Test
    @DisplayName("get/setFactor: check for illegalArgumentsException")
    void getFactorCheckIfExpectetValueIsTheSame(){
        Assertions.assertThrows(IllegalArgumentException.class,()->pkw.setFactor(0));
        Assertions.assertThrows(IllegalArgumentException.class,()->pkw.setFactor(-1));
    }
}