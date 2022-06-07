package parkhouse.views;

import parkhouse.models.IParkingModel;

public class CurrentElementsView implements IObserver {

    private final IParkingModel model;
    private long currentTimeByLastCar;

    public CurrentElementsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {
        currentTimeByLastCar = model.currentTimeByLastCar();
    }

    public double currentTimeByLastCar() {
        return currentTimeByLastCar;
    }

    @Override
    public String toString() {
        return "";
    }
}
