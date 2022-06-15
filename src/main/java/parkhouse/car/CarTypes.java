package parkhouse.car;

import java.util.HashMap;
import java.util.Map;

public class CarTypes {

    private static final Map<String, CarTypes> instances = new HashMap<String, CarTypes>();



    private CarTypes(){

    }

    public static CarTypes getInstance(String key){
        synchronized (instances){

            CarTypes instance = instances.get(key);

            if(instance == null){
                instance = new CarTypes();
                instances.put(key, instance);
            }
            return instance;
        }
    }


}