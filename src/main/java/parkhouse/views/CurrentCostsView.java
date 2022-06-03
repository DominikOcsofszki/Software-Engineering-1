package parkhouse.views;

import parkhouse.models.IParkingModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CurrentCostsView implements IObserver {

    private IParkingModel model;
    private Map<String,Double> currentCosts;

    public CurrentCostsView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
        currentCosts = new HashMap<>();
    }

    @Override
    public void update() {

    }

    public void showCurrentCosts() {
        for (Map.Entry<String,Double> entry : currentCosts.entrySet()) {
            System.out.println(
                    "License: " + entry.getKey() +
                    "\nPrice: " + entry.getValue() +
                    "\n======="
            );
        }
    }
}
