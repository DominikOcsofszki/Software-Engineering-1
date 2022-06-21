package parkhouse.util;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;
import parkhouse.logging.Log;
import parkhouse.security.SanitizedCar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Saver {

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

    public static void saveCars(IParkingController controller) {        //ToDo Save an extra List for testing?
        Path path = Paths.get("saved_cars.save");
        try {
            BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.US_ASCII);
            for (ICar c : controller.getAllCars()) {
                bw.write(c.toString() + "\n");
            }
            bw.close();
        } catch (IOException e) {       //ToDo How to catch this Error in Testing?
            Log.getLogger().log(Level.WARNING, "Save Cars Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadCars(IParkingController controller) {
//        Path path = Paths.get("//saved_cars.save");       //ToDo seems to make problems on diff Systems
        Path path = Paths.get("saved_cars.save");       //ToDo seems to make problems on diff Systems
        try {
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
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
            br.close();
        } catch (IOException e) {
            Log.getLogger().log(Level.WARNING, "Load Cars Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
