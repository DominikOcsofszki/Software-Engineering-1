package parkhouse.util;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.security.SanitizedCar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Saver {

    private static final Logger LOGGER = Logger.getLogger(Saver.class.getName());

    private Saver() {
    }

    private static boolean init = true;

    public static boolean init() {
        if (init) {
            init = false;
            return true;
        }
        return false;
    }

    public static void saveCars(IParkingController controller, String name) {
        Path path = Paths.get("src/main/resources/" + name + ".cars");
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.US_ASCII)) {
            for (ICar c : controller.getAllCars()) {
                bw.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            LOGGER.warning("Save Cars Failed: " + e.getMessage());
        }
    }

    public static void loadCars(IParkingController controller, String name) {
        Path path = Paths.get("src/main/resources/" + name + ".cars");
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            List<String> lines = new ArrayList<>();
            br.lines().collect(Collectors.toCollection(() -> lines));
            for (String l : lines) {
                ICar car = new SanitizedCar(new Car(l.split("/")));
                if (!car.gone()) {
                    controller.addCar(car);
                } else {
                    controller.addRemovedCarRestartServer(car);
                }
            }
        } catch (IOException e) {
            LOGGER.warning("Load Cars Failed: " + e.getMessage());
        }
    }
}
