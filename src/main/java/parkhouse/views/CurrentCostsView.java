package parkhouse.views;

import parkhouse.calculations.Price;
import parkhouse.models.IParkingModel;
import parkhouse.util.Tableize;

import java.util.HashMap;
import java.util.Map;

public class CurrentCostsView implements IObserver {

    private final IParkingModel model;
    private HashMap<String,Double> currentCosts;

    public CurrentCostsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
        currentCosts = new HashMap<>();
    }

    @Override
    public void update() {
        this.currentCosts = model.currentCost();
    }

    public HashMap<String, Double> getCurrentCosts() {
        return currentCosts;
    }

    @Override
    public String toString() {
        String[] headers = new String[currentCosts.size()];
        String[][] data = new String[1][currentCosts.size()];
        int i = 0;
        for (Map.Entry<String,Double> e : currentCosts.entrySet()) {
            headers[i] = e.getKey();
            data[0][i++] = Price.format(Price.out(e.getValue()));
        }
        return Tableize.table(headers, data);
    }
}
