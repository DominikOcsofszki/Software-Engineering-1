package parkhouse.views;

import parkhouse.calculations.Price;
import parkhouse.models.IParkingModel;

public class WeeklyEarningsView implements IObserver{

    private final IParkingModel model;
    private double weeklyEarnings;

    public WeeklyEarningsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {
        weeklyEarnings = model.weeklyEarnings();
    }

    public double getWeeklyEarnings() {
        return weeklyEarnings;
    }

    @Override
    public String toString() {
        return Price.format(weeklyEarnings);
    }
}
