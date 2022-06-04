package parkhouse.models;

import java.util.List;

public interface IParkingModel extends IObservable {

    void addCar(String[] params);
    void removeCar(String[] params);

    Double dailyEarnings();
    Double weeklyEarnings();
    List<String> currentCost();
    List<String> licensePlates();

}
