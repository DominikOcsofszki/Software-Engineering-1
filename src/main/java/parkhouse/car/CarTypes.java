package parkhouse.car;

import parkhouse.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class CarTypes {

    private static final Map<String, CarTypes> instances = new HashMap<>();

    private CarTypes(){
    }

    public static CarTypes getInstance(String key){
        synchronized (instances){

            CarTypes instance = instances.get(key);

            if(instance == null){
                instance = new CarTypes();
                instances.put(key, instance);
                Log.getLogger().log(Level.INFO, "Created new Car instance: " + key);
            }
            return instance;
        }
    }


}