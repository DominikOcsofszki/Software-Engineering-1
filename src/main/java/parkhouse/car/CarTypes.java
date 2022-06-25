package parkhouse.car;

import java.util.HashMap;
import java.util.Map;

public class CarTypes {

    private static final Map<String, CarTypes> types = new HashMap<>();
    private double factor;

    private CarTypes() {
    }

    public static CarTypes getInstance(String key){
            CarTypes type = types.get(key);
            if (type == null){
                type = new CarTypes();
                types.put(key, type);
            }
            return type;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        if(factor<=0){
            throw new IllegalArgumentException();
        }
        this.factor = factor; }

}