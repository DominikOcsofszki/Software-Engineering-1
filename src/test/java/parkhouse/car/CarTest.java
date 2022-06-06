package parkhouse.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    ICar c1;
    ICar c2;

    String[] params1 = {"1","1650969214942","_","_","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","1","Frau","PKW","SU-S 8","16509697749492"};
    String[] params2 = {"6","1650969215214","_","_","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"};
    String[] update1 = {"1","1650969214942","5","6","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","1","Frau","PKW","SU-S 8","16509697749492"};
    String[] update2 = {"6","1650969215214","8","9","9fbb53684b77f16f9e88faa9e7d63d2b","#0c0f15","9","Frau","PKW","SU-S 8","16509697749492"};

    @BeforeEach
    void setUp() {
        c1 = new Car(params1);
        c2 = new Car(params2);
    }

    @Test
    void nr() {
        assertEquals(1, c1.nr());
        assertEquals(6, c2.nr());
    }


    @Test
    void begin() {
        assertEquals(1650969214942L, c1.begin());
        assertEquals(1650969215214L, c2.begin());
    }

    @Test
    void end() {
        c2.updateParams(update2);
        assertEquals(1650969215214L + 8, c2.end());
    }

    @Test
    void duration() {
        c2.updateParams(update2);
        assertEquals(8, c2.duration());
    }

    @Test
    void price() {
        c2.updateParams(update2);
        assertEquals(9, c2.price());
    }

    @Test
    void updateParams() {
        c2.updateParams(update2);
        assertEquals(8, c2.duration());
    }

    @Test
    void testToString() {
        assertEquals(Arrays.toString(params1), c1.toString());
    }
}