package parkhouse;

import parkhouse.car.Car;
import parkhouse.car.ICar;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Data {

    private Data() {}

    public static List<String[]> params() {
        Path path = Paths.get("cars.csv");
        List<String[]> params = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
            List<String> lines = new ArrayList<>();
            br.lines().collect(Collectors.toCollection(() -> lines));
            for (String l : lines) {
                params.add(l.split(","));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return params;
    }

    public static List<String[]> paramsDuration() {
        Path path = Paths.get("cars_with_duration.csv");
        List<String[]> params = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
            List<String> lines = new ArrayList<>();
            br.lines().collect(Collectors.toCollection(() -> lines));
            for (String l : lines) {
                params.add(l.split(","));
            }
        } catch(IOException e) {
            e.printStackTrace();
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

}
