package parkhouse.views;

import parkhouse.models.IParkingModel;

public class DailyEarningsView implements IObserver {

    private IParkingModel model;
    private double dailyEarnings;

    public DailyEarningsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {

    }

    public double dailyEarnings() {
        return this.dailyEarnings;
    }
}
