package parkhouse.util;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.security.SanitizedCar;
import parkhouse.servlets.MainServlet;

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

    /*
    Author: jstueh2s & docsof2s
     */

    private Saver() {}

    private static boolean init = true;
    private static boolean initConfig = true;

    public static boolean init() {
        if (init) {
            init = false;
            return true;
        }
        return false;
    }
    public static boolean initConfig() {
        if (initConfig) {
            initConfig = false;
            return true;
        }
        return false;
    }

    public static void saveCars(IParkingController controller, String name) {
        saveConfig(name);
        Path path = Paths.get("src/main/resources/" + name + ".cars");
//        //ToDo for Testing:
//        if(name.equals("MainServletTest")) {
//            path = Paths.get("src/test/resources/" + name + ".cars");
//        }
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.US_ASCII)) {
            for (ICar c : controller.getAllCars()) {
                bw.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            LOGGER.warning("Save Cars Failed: " + e.getMessage());
        }
    }


    public static void loadCars(IParkingController controller, String name) {
        loadConfig(name);
        Path path = Paths.get("src/main/resources/" + name + ".cars");
//        //ToDo for Testing: add lines here or need extra testing ressources in normal folder?
//        System.out.println(name);
//        if(name.equals("MainServletTest")) {
//            path = Paths.get("src/test/resources/" + name + ".cars");
//        }
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            List<String> lines = new ArrayList<>();
            br.lines().collect(Collectors.toCollection(() -> lines));
            for (String l : lines) {
                ICar car = new SanitizedCar(new Car(l.split("/")));
                if (!car.gone()) {
                    controller.addCar(car);
                } else {
                    controller.addRemovedCar(car);
                }
            }
        } catch (IOException e) {
            LOGGER.warning("Load Cars Failed: " + e.getMessage());
        }
    }
    //------

    public static void saveConfig(String name) {
        Path path = Paths.get("src/main/resources/" + name + ".conf");
//        //ToDo for Testing:
//        if(name.equals("MainServletTest")) {
//            path = Paths.get("src/test/resources/" + name + ".conf");
//        }
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.US_ASCII)) {
            /*for (ICar c : controller.getAllCars()) {
                bw.write(c.toString() + "\n");
            }*/
            bw.write(Config.maxCars + "\n");
            bw.write(Config.openFrom + "\n");
            bw.write(Config.openTo + "\n");

        } catch (IOException e) {
            LOGGER.warning("Save Config Failed: " + e.getMessage());
        }
    }

    public static void loadConfig(String name) {
        Path path = Paths.get("src/main/resources/" + name + ".conf");
//        //ToDo for Testing:
//        if(name.equals("MainServletTest")) {
//            path = Paths.get("src/test/resources/" + name + ".conf");
//        }
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
//            List<String> lines = new ArrayList<>();
//            br.lines().collect(Collectors.toCollection(() -> lines));
            Config.setMaxCars(Integer.parseInt(br.readLine()));
            Config.setOpenFrom(Integer.parseInt(br.readLine()));
            Config.setOpenTo(Integer.parseInt(br.readLine()));

        } catch (IOException e) {
            LOGGER.warning("Load Config Failed: " + e.getMessage());
        }
    }
    //---
}
