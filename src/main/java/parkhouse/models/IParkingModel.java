package parkhouse.models;

import parkhouse.car.ICar;

public interface IParkingModel extends IObservable {

    void addCar(String[] params);
    void removeCar(String[] params);

}
