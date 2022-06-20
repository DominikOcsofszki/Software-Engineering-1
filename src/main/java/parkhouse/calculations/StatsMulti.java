package parkhouse.calculations;

import parkhouse.controller.IParkingController;
import parkhouse.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class StatsMulti {

    private static final Map<String, AbstractStats> INSTANCE_MAP = new HashMap<>();

    private StatsMulti() {
    }

    public static AbstractStats getInstance(String key, IParkingController pk) {
        synchronized (INSTANCE_MAP) {

            AbstractStats instance = INSTANCE_MAP.get(key);

            if (instance == null) {
                instance = chooseClass(key, pk);
                INSTANCE_MAP.put(key, instance);
                Log.getLogger().log(Level.INFO, "Created new Car instance: " + key);
            }
            return instance;
        }
    }

    private static AbstractStats chooseClass(String s, IParkingController pk) {
        switch (s) {
            case "StatsAllCarsAvg":
                return new StatsAllCarsAvg(pk);
            case "Sum":
                return new StatsAllCarsSum(pk);
            case "StatsInHouseAvg":
                return new StatsInHouseAvg(pk);
            case "StatsInHouseSum":
                return new StatsInHouseSum(pk);
            case "StatsRemovedAvg":
                return new StatsRemovedAvg(pk);
            case "StatsRemovedSum":
                return new StatsRemovedSum(pk);
            default:
                throw new IllegalArgumentException("Wrong name!");
        }

    }


}