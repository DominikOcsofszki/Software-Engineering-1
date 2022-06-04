package parkhouse.models;

public interface IParkingModel extends IObservable {

    void addCar(String[] params);
    void removeCar(String[] params);

}
