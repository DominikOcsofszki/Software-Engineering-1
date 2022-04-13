import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParkTimerTest {

    private ParkTimer parkTimer;

    @BeforeEach
    void setUp() {
        parkTimer = new ParkTimer();
    }

    @Test
    @DisplayName("")
    void registerVehicle_size() {
        parkTimer.registerVehicle("0", new Time(0, 0, 1));
        assertEquals(1, parkTimer.getRegistered().size());
    }

    @Test
    @DisplayName("")
    void unregisterVehicle_size() {
        parkTimer.registerVehicle("0", new Time(0, 0, 1));
        parkTimer.unregisterVehicle(parkTimer.getRegistered().get(0)[0]);
        assertEquals(0, parkTimer.getRegistered().size());
    }

}
