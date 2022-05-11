package parkhouse;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Car implements CarIF {
    String[] params;
    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return Integer.parseInt(params[0]);
    }

    @Override
    public long begin() {
        return Long.parseLong(params[1]);
    }

    @Override
    public long end() {
        return this.begin() + this.duration();
    }

    @Override
    public long duration() {
        if (params[2].equals("_")) {
            return 0;
        }
        return Long.parseLong(params[2]);
    }

    @Override
    public double price() {
        return Double.parseDouble(params[3]);
    }

    @Override
    public void updateParams(String[] params) {
        this.params = params;
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }

    public static JsonArray getNrArray(List<CarIF> cars) {
        JsonArrayBuilder nrArray = Json.createArrayBuilder();
        for (CarIF c : cars) {
            nrArray.add(Json.createValue(c.nr()));
        }
        return nrArray.build();
    }

    public static JsonArray getDurationArray(List<CarIF> cars) {
        JsonArrayBuilder durationArray = Json.createArrayBuilder();
        for (CarIF c : cars) {
            durationArray.add(Json.createValue(c.duration()));
        }
        return durationArray.build();
    }
}
