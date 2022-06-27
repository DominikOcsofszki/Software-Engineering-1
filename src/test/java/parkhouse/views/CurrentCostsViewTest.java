package parkhouse.views;


import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.models.IParkingModel;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import parkhouse.models.ParkingModel;
import parkhouse.util.Time;

public class CurrentCostsViewTest {

    private IParkingModel parkingModel;
    private CurrentCostsView currentCostsView;
    private final ICar x = new Car(new String[]{"25", Time.now() - 10000 + "", "6010", "69", "a7aa53882766f4bf361ca339fb843fa9",
            "#42671f", "2", "Women", "SUV", "SU-K 69", Time.now() - 10000 + ""});
    private final ICar x1 = new Car(new String[]{"25", Time.now() - 10000 + "", "6010", "70", "a7aa53882766f4bf361ca339fb843fa9",
            "#42671f", "2", "Women", "SUV", "SU-K 70", Time.now() - 10000 + ""});
    private final ICar x2 = new Car(new String[]{"25", Time.now() - 10000 + "", "6010", "71", "a7aa53882766f4bf361ca339fb843fa9",
            "#42671f", "2", "Women", "SUV", "SU-K 71", Time.now() - 10000 + ""});

    @BeforeEach
    void setup() {
        parkingModel = new ParkingModel();
        currentCostsView = new CurrentCostsView(parkingModel);
    }

    @AfterEach
    void teardown() {
        parkingModel = null;
        currentCostsView = null;
    }

    @Test
    @DisplayName("test if the currentCostsView gets updated")
    void currentCostsViewTest() {
        parkingModel.registerObserver(currentCostsView);

        parkingModel.addCar(x);
        parkingModel.addCar(x1);
        parkingModel.addCar(x2);
        assertEquals(69, currentCostsView.getCurrentCosts().get("SU-K 69"));
        assertEquals(70, currentCostsView.getCurrentCosts().get("SU-K 70"));
        assertEquals(71, currentCostsView.getCurrentCosts().get("SU-K 71"));
    }

    @Test
    @DisplayName("test if the format is right")
    void currentCostsViewToStringTest() {
        parkingModel.registerObserver(currentCostsView);
        parkingModel.addCar(x);
        boolean contains = currentCostsView.toString().contains("0.69€");
        assertTrue(contains);


        parkingModel.addCar(x1);
        boolean contains1 = currentCostsView.toString().contains("0.70€");
        assertTrue(contains1);


        parkingModel.addCar(x2);
        boolean contains2 = currentCostsView.toString().contains("0.71€");
        assertTrue(contains2);

    }

}
