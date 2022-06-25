package parkhouse;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Data {

    private static final Logger LOGGER = Logger.getLogger(Data.class.getName());

    private Data() {}

    public static List<String[]> params() {
        return loadParams("cars.csv");
    }

    public static List<String[]> paramsDuration() {
        return loadParams("cars_with_duration.csv");
    }

    private static List<String[]> loadParams(String p) {
        Path path = Paths.get(p);
        List<String[]> params = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            List<String> lines = new ArrayList<>();
            br.lines().collect(Collectors.toCollection(() -> lines));
            for (String l : lines) {
                params.add(l.split(","));
            }
        } catch(IOException e) {
            LOGGER.warning("Load Data Failed: " + e.getMessage());
        }
        return params;
    }

    public static List<ICar> cars() {
        List<String[]> params = params();
        List<ICar> cars = new ArrayList<>();
        for (String[] p : params) {
            cars.add(new Car(p));
        }
        return cars;
    }

    public static IParkingController controller() {
        List<ICar> cars = cars();
        IParkingController controller = new ParkingController();
        for (ICar c : cars) {
            if (c.gone()) {
                controller.addRemovedCarRestartServer(c);
            } else {
                controller.addCar(c);
            }
        }
        return controller;
    }

}
