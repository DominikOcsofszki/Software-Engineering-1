import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    private TimeIF t0, t1, t2;

    @BeforeEach
    public void setup() {
        t0 = new Time();
        t1 = new Time(12, 30, 20);
        t2 = new Time("15:10:5");
    }

    @AfterEach
    public void teardown() {
        t0 = null;
        t1 = null;
        t2 = null;
    }

    @Test
    @DisplayName("Test if the empty constructor produced the correct object")
    public void constructor_empty() {
        assertEquals(0, t0.getHours(), "Hours were not initialized correctly");
        assertEquals(0, t0.getMinutes(), "Minutes were not initialized correctly");
        assertEquals(0, t0.getSeconds(), "Seconds were not initialized correctly");
    }

    @Test
    @DisplayName("Test if the hours/minutes/seconds constructor produced the correct object")
    public void constructor_hoursMinutesSeconds() {
        assertEquals(12, t1.getHours(), "Hours were not initialized correctly");
        assertEquals(30, t1.getMinutes(), "Minutes were not initialized correctly");
        assertEquals(20, t1.getSeconds(), "Seconds were not initialized correctly");
    }

    @Test
    @DisplayName("Test if the string constructor produced the correct object")
    public void constructor_fromString() {
        assertEquals(15, t2.getHours(), "Hours were not initialized correctly");
        assertEquals(10, t2.getMinutes(), "Minutes were not initialized correctly");
        assertEquals(5, t2.getSeconds(), "Seconds were not initialized correctly");
    }

    @Test
    @DisplayName("Test if the string constructor throws exceptions")
    public void constructor_fromString_throwException() {
        assertThrows(IllegalArgumentException.class, () -> {
            TimeIF t3 = new Time("123020");
        }, "Constructor does not throw exception because of wrong format");
        assertThrows(IllegalArgumentException.class, () -> {
            TimeIF t3 = new Time("h:m:s");
        }, "Constructor does not throw exception because of invalid characters");
    }

    @Test
    @DisplayName("Test if positive hours below 24 are set correctly")
    public void setHours_basic() {
        t0.setHours(16);
        assertEquals(16, t0.getHours(), "Hours were not set correctly");
    }

    @Test
    @DisplayName("Test if positive hours above 24 are set correctly")
    public void setHours_large() {
        t0.setHours(43);
        assertEquals(19, t0.getHours(), "Hours were not set correctly");
    }

    @Test
    @DisplayName("Test if negative hours are set correctly")
    public void setHours_negative() {
        t0.setHours(-6);
        assertEquals(18, t0.getHours(), "Hours were not set correctly");
    }

    @Test
    @DisplayName("Test if positive minutes below 60 are set correctly")
    public void setMinutes_basic() {
        t0.setMinutes(16);
        assertEquals(16, t0.getMinutes(), "Minutes were not set correctly");
    }

    @Test
    @DisplayName("Test if positive minutes above 60 are set correctly")
    public void setMinutes_large() {
        t0.setMinutes(87);
        assertEquals(27, t0.getMinutes(), "Minutes were not set correctly");
    }

    @Test
    @DisplayName("Test if negative minutes are set correctly")
    public void setMinutes_negative() {
        t0.setMinutes(-6);
        assertEquals(54, t0.getMinutes(), "Minutes were not set correctly");
    }

    @Test
    @DisplayName("Test if positive minutes above 60 increment hours")
    public void setMinutes_largeIncrementHours() {
        t0.setMinutes(87);
        assertEquals(1, t0.getHours(), "Hours were not incremented correctly");
    }

    @Test
    @DisplayName("Test if negative minutes decrement hours")
    public void setMinutes_negativeDecrementHours() {
        t0.setMinutes(-30);
        assertEquals(23, t0.getHours(), "Hours were not decremented correctly");
    }

    @Test
    @DisplayName("Test if positive seconds below 60 are set correctly")
    public void setSeconds_basic() {
        t0.setSeconds(16);
        assertEquals(16, t0.getSeconds(), "Seconds were not set correctly");
    }

    @Test
    @DisplayName("Test if seconds minutes above 60 are set correctly")
    public void setSeconds_large() {
        t0.setSeconds(87);
        assertEquals(27, t0.getSeconds(), "Seconds were not set correctly");
    }

    @Test
    @DisplayName("Test if seconds minutes are set correctly")
    public void setSeconds_negative() {
        t0.setSeconds(-6);
        assertEquals(54, t0.getSeconds(), "Seconds were not set correctly");
    }

    @Test
    @DisplayName("Test if positive seconds above 60 increment minutes")
    public void setSeconds_largeIncrementMinutes() {
        t0.setSeconds(87);
        assertEquals(1, t0.getMinutes(), "Minutes were not incremented correctly");
    }

    @Test
    @DisplayName("Test if negative seconds decrement minutes")
    public void setSeconds_negativeDecrementMinutes() {
        t0.setSeconds(-30);
        assertEquals(59, t0.getMinutes(), "Minutes were not decremented correctly");
    }

    @Test
    @DisplayName("Test if positive hours are added correctly")
    public void addHours_positive() {
        t0.addHours(10);
        assertEquals(10, t0.getHours(), "Hours were not added correctly");
    }

    @Test
    @DisplayName("Test if negative hours are added correctly")
    public void addHours_negative() {
        t0.addHours(-3);
        assertEquals(21, t0.getHours(), "Hours were not added correctly");
    }

    @Test
    @DisplayName("Test if positive hours are subtracted correctly")
    public void subHours_positive() {
        t0.subHours(10);
        assertEquals(14, t0.getHours(), "Hours were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if negative hours are subtracted correctly")
    public void subHours_negative() {
        t0.subHours(-3);
        assertEquals(3, t0.getHours(), "Hours were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if positive minutes are added correctly")
    public void addMinutes_positive() {
        t0.addMinutes(30);
        assertEquals(30, t0.getMinutes(), "Minutes were not added correctly");
    }

    @Test
    @DisplayName("Test if negative minutes are added correctly")
    public void addMinutes_negative() {
        t0.addMinutes(-10);
        assertEquals(50, t0.getMinutes(), "Minutes were not added correctly");
    }

    @Test
    @DisplayName("Test if positive minutes are subtracted correctly")
    public void subMinutes_positive() {
        t0.subMinutes(30);
        assertEquals(30, t0.getMinutes(), "Minutes were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if negative minutes are subtracted correctly")
    public void subMinutes_negative() {
        t0.subMinutes(-10);
        assertEquals(10, t0.getMinutes(), "Minutes were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if positive seconds are added correctly")
    public void addSeconds_positive() {
        t0.addSeconds(30);
        assertEquals(30, t0.getSeconds(), "Seconds were not added correctly");
    }

    @Test
    @DisplayName("Test if negative seconds are added correctly")
    public void addSeconds_negative() {
        t0.addSeconds(-10);
        assertEquals(50, t0.getSeconds(), "Seconds were not added correctly");
    }

    @Test
    @DisplayName("Test if positive seconds are subtracted correctly")
    public void subSeconds_positive() {
        t0.subSeconds(30);
        assertEquals(30, t0.getSeconds(), "Seconds were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if negative seconds are subtracted correctly")
    public void subSeconds_negative() {
        t0.subSeconds(-10);
        assertEquals(10, t0.getSeconds(), "Seconds were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if time objects are added together correctly")
    public void add_basic() {
        t1.add(t2);
        assertEquals(3, t1.getHours(), "Hours were not added correctly");
        assertEquals(40, t1.getMinutes(), "Minutes were not added correctly");
        assertEquals(25, t1.getSeconds(), "Seconds were not added correctly");
    }

    @Test
    @DisplayName("Test if time objects are subtracted together correctly")
    public void sub_basic() {
        t1.sub(t2);
        assertEquals(21, t1.getHours(), "Hours were not subtracted correctly");
        assertEquals(20, t1.getMinutes(), "Minutes were not subtracted correctly");
        assertEquals(15, t1.getSeconds(), "Seconds were not subtracted correctly");
    }

    @Test
    @DisplayName("Test if difference between time objects is calculated correctly")
    public void diff_basic() {
        TimeIF t3 = t1.diff(t2);
        assertEquals(3, t3.getHours(), "Difference between hours is not correct");
        assertEquals(20, t3.getMinutes(), "Difference between minutes is not correct");
        assertEquals(15, t3.getSeconds(), "Difference between seconds is not correct");
    }

    @Test
    @DisplayName("Test if time string is build correctly")
    public void toString_basic() {
        assertEquals("12:30:20", t1.toString(), "Time string is not build correctly");
    }

    @Test
    @DisplayName("Test if time string is build with leading zeroes")
    public void toString_leadingZero() {
        assertEquals("00:00:00", t0.toString(), "Time string is not build correctly");
    }

    @Test
    @DisplayName("Test if time objects are compared correctly (greater)")
    public void compareTo_greater() {
        assertEquals(1, t2.compareTo(t1), "Time objects were not compared correctly");
    }

    @Test
    @DisplayName("Test if time objects are compared correctly (lesser)")
    public void compareTo_lesser() {
        assertEquals(-1, t1.compareTo(t2), "Time objects were not compared correctly");
    }

    @Test
    @DisplayName("Test if time objects are compared correctly (equal)")
    public void compareTo_equal() {
        TimeIF t3 = new Time(12, 30, 20);
        assertEquals(0, t1.compareTo(t3), "Time objects were not compared correctly");
    }

    @Test
    @DisplayName("Test if objects are compared correctly (equal)")
    public void equals_equal() {
        TimeIF t3 = new Time(12, 30, 20);
        assertEquals(t1, t3, "Time objects were not compared correctly");
    }

    @Test
    @DisplayName("Test if objects are compared correctly (not equal)")
    public void equals_not_equal() {
        assertNotEquals(t1, t2, "Time objects were not compared correctly");
    }

}
