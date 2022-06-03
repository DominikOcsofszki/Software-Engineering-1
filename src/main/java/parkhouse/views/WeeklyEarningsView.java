package parkhouse.views;

import parkhouse.models.IParkingModel;

public class WeeklyEarningsView implements IObserver{

    private IParkingModel model;
    private double weeklyEarnings;

    public WeeklyEarningsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {

    }

    public double weeklyEarnings() {
        return this.weeklyEarnings;
    }
}
