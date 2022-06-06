package parkhouse.views;

import parkhouse.models.IParkingModel;

public class DailyEarningsView implements IObserver {

    private final IParkingModel model;
    private double dailyEarnings;

    public DailyEarningsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {
        dailyEarnings = model.dailyEarnings();
    }

    public double getDailyEarnings() {
        return dailyEarnings;
    }

    @Override
    public String toString() {
        return "";
    }
}
