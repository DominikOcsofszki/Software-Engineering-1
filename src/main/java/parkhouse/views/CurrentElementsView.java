package parkhouse.views;

import parkhouse.config.Config;
import parkhouse.models.IParkingModel;

import java.util.Arrays;

public class CurrentElementsView implements IObserver {
    // ToDo Still needed? Seems unnecessary!

    private final IParkingModel model;
    private long currentTimeByLastCar;
    private int[] usedParkingSpots;
    private int maxParking;

    public CurrentElementsView(IParkingModel model) {
        this.model = model;
        this.maxParking = Config.MAX_CARS;
        this.usedParkingSpots = new int[maxParking];
        model.registerObserver(this);
    }

    @Override
    public void update() {
        if(maxParking != Config.MAX_CARS) {
            maxParking = Config.MAX_CARS;
            int[] tmp = new int[maxParking];
            tmp = Arrays.copyOfRange(usedParkingSpots, 0, maxParking);
            usedParkingSpots = tmp;
        }
        currentTimeByLastCar = model.lastTimeExixtOrEnterCar();
    }

    public double currentTimeByLastCar() {
        return currentTimeByLastCar;
    }
    public int[] usedParkingSpots() {
        return usedParkingSpots;
    }
    public int maxParking() {
        return maxParking;
    }


    @Override
    public String toString() {
        return "";
    }
}
