package parkhouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    CarIF c1;
    CarIF c2;

    String[] paramsKunde1e = {"1","1650969214942","_","_","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","1","Frau","PKW","SU-S 8","16509697749492"};;
    String[] paramsKunde6e = {"6","1650969214942","_","_","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"};
    String[] params6l = {"6","1650969214942","8","8","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"};;

    @BeforeEach
    void setUp() {
        c1 = new Car(paramsKunde1e);
        c2 = new Car(paramsKunde6e);
    }

    @Test
    void nr() {
        assertEquals(1, c1.nr());
        assertEquals(6, c2.nr());
    }


    @Test
    void begin() {
        assertEquals(1650969214942l, c1.begin());
        assertEquals(1650969214942l, c2.begin());
    }

    @Test
    void end() {
        c2.updateParams(params6l);
        assertEquals(1650969214942l + 8, c2.end());
    }

    @Test
    void duration() {
        c2.updateParams(params6l);
        assertEquals(8, c2.duration());
    }

    @Test
    void price() {
        c2.updateParams(params6l);
        assertEquals(8, c2.price());
    }

    @Test
    void updateParams() {
        c2.updateParams(params6l);
        assertEquals(8, c2.duration());
    }

    @Test
    void testToString() {
        assertEquals(Arrays.toString(paramsKunde1e), c1.toString());
    }
}