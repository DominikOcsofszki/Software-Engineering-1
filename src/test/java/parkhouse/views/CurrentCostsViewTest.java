package parkhouse.views;

        import parkhouse.Data;
        import parkhouse.car.Car;
        import parkhouse.car.ICar;
        import parkhouse.models.IParkingModel;

        import java.util.List;

        import org.junit.jupiter.api.*;
        import static org.junit.jupiter.api.Assertions.*;
        import parkhouse.models.ParkingModel;
        import parkhouse.util.Time;

public class CurrentCostsViewTest {

    private final List<String[]> params = Data.paramsDuration();
    private IParkingModel parkingModel;
    private DailyEarningsView dailyEarningsView;

    @BeforeEach
    void setUp() {
        parkingModel = new ParkingModel();
        dailyEarningsView = new DailyEarningsView(parkingModel);
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
        dailyEarningsView = null;
    }

    @Test
    @DisplayName("test if the dailyEarningsView gets updated")
    void dailyEarningView_getDailyEarnings_test() {
        parkingModel.registerObserver(dailyEarningsView);
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals(69, dailyEarningsView.getDailyEarnings());
    }

    @Test
    @DisplayName("test if the format is right")
    void dailyEarningsView_toString_test() {
        parkingModel.registerObserver(dailyEarningsView);
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        parkingModel.removeCar(x);
        assertEquals("0.69€", dailyEarningsView.toString());
    }

}
