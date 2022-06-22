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
    private CurrentCostsView currentCostsView;

    @BeforeEach
    void setUp() {
        parkingModel = new ParkingModel();
        currentCostsView = new CurrentCostsView(parkingModel);
    }

    @AfterEach
    void tearDown() {
        parkingModel = null;
        currentCostsView = null;
    }

    @Test
    @DisplayName("test if the currentCostsView gets updated")       //ToDo check again
    void currentCostsView_test() {
        /*parkingModel.registerObserver(currentCostsView);
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        assertEquals(69, currentCostsView.getCurrentCosts().get("SU-K 41"));
*/

        parkingModel.registerObserver(currentCostsView);
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 69",Time.now() - 10000+""});
        ICar x1 = new Car(new String[]{"25", Time.now() - 10000+"","6010","70","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 70",Time.now() - 10000+""});
        ICar x2 = new Car(new String[]{"25", Time.now() - 10000+"","6010","71","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 71",Time.now() - 10000+""});
        parkingModel.addCar(x);
        parkingModel.addCar(x1);
        parkingModel.addCar(x2);
        assertEquals(69, currentCostsView.getCurrentCosts().get("SU-K 69"));
        assertEquals(70, currentCostsView.getCurrentCosts().get("SU-K 70"));
        assertEquals(71, currentCostsView.getCurrentCosts().get("SU-K 71"));
    }

    @Test
    @DisplayName("test if the format is right")     //ToDo toString gibt immer nur eine Tabelle aus. korrekt getestet?
    void currentCostsView_toString_test() {
        parkingModel.registerObserver(currentCostsView);
        ICar x = new Car(new String[]{"25", Time.now() - 10000+"","6010","69","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        ICar x1 = new Car(new String[]{"25", Time.now() - 10000+"","6010","70","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        ICar x2 = new Car(new String[]{"25", Time.now() - 10000+"","6010","71","a7aa53882766f4bf361ca339fb843fa9",
                "#42671f","2","Women","SUV","SU-K 41",Time.now() - 10000+""});
        parkingModel.addCar(x);
        boolean contains = currentCostsView.toString().contains("0.69€");
        System.out.println(currentCostsView.toString());
        assertTrue(contains);


        parkingModel.addCar(x1);
        boolean contains1 = currentCostsView.toString().contains("0.70€");
        System.out.println(currentCostsView.toString());
        assertTrue(contains1);


        parkingModel.addCar(x2);
        boolean contains2 = currentCostsView.toString().contains("0.71€");
        System.out.println(currentCostsView.toString());
        assertTrue(contains2);


//        boolean contains = currentCostsView.toString().contains("0.69€");
//        boolean contains1 = currentCostsView.toString().contains("0.70€");
//        boolean contains2 = currentCostsView.toString().contains("0.71€");
//        System.out.println(currentCostsView.toString());

//        assertEquals("0.69€", currentCostsView.toString());
//        parkingModel.removeCar(x);

    }

}
