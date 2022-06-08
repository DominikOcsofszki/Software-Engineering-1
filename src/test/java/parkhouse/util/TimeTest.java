package parkhouse.util;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    void time_difference_test() {
        assertEquals(0, Time.difference(50, 50));
        assertEquals(100, Time.difference(50, -50));
        assertEquals(200, Time.difference(50, -150));
        assertEquals(0, Time.difference(-50, -50));
        assertEquals(100, Time.difference(-50, 50));
        assertEquals(200, Time.difference(-150, 50));
    }

}
