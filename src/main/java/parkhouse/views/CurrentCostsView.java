package parkhouse.views;

import parkhouse.models.IParkingModel;
import parkhouse.util.Tableize;

import java.util.ArrayList;
import java.util.List;

public class CurrentCostsView implements IObserver {

    private final IParkingModel model;
    private List<String> currentCosts;
    private List<String> licensePlates;

    public CurrentCostsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
        currentCosts = new ArrayList<>();
        licensePlates = new ArrayList<>();
    }

    @Override
    public void update() {
        this.currentCosts = model.currentCost();
        this.licensePlates = model.licensePlates();
    }

    public double getCurrentCosts() {
        double sum = 0;
        for(String s : currentCosts) {
            sum += Double.parseDouble(s);
        }
        return sum;
    }

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    @Override
    public String toString() {
        String[][] data = new String[1][currentCosts.size()];
        for (int i = 0; i < currentCosts.size(); i++) {
            data[0][i] = currentCosts.get(i);
        }
        return Tableize.table(licensePlates.toArray(new String[0]), data);
    }
}
