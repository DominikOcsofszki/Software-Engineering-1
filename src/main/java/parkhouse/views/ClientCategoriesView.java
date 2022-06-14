package parkhouse.views;

import parkhouse.car.ICar;
import parkhouse.models.IParkingModel;
import parkhouse.util.Jsonify;

import javax.json.JsonObject;
import java.util.List;

public class ClientCategoriesView implements IObserver {

    private final IParkingModel model;
    private List<ICar> cars;

    public ClientCategoriesView(IParkingModel model) {
        this.model = model;
        model.registerObserver(this);
    }

    @Override
    public void update() {
        this.cars = model.getCars();
    }

    @Override
    public String toString() {
        JsonObject categories = Jsonify.carsCount(cars, ICar::category);
        return Jsonify.plot(
                Jsonify.getKeys(categories),
                Jsonify.getValues(categories),
                "bar", "Categories").toString();
    }
}
