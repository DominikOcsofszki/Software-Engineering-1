package parkhouse.views;

import parkhouse.calculations.Price;
import parkhouse.models.IParkingModel;

public class DailyEarningsView implements IObserver {

    private final IParkingModel model;
    private double dailyEarnings;

    public DailyEarningsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    public double getDailyEarnings() {
        return dailyEarnings;
    }

    @Override
    public void update() {
        dailyEarnings = model.dailyEarnings();
    }

    @Override
    public String toString() {
        return Price.format(dailyEarnings);
    }
}
