package parkhouse.models;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.ticket.ITicket;
import parkhouse.views.IObserver;

import java.util.List;

public class ParkingModel implements IParkingModel {

    private List<ICar> cars;
    private List<ITicket> tickets;
    private List<IObserver> observers;


    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update();
        }
    }

    @Override
    public void addCar(String[] params) {
        ICar car = new Car(params);
        cars.add(car);
        notifyObservers();
    }

    @Override
    public void removeCar(String[] params) {
        ICar car = new Car(params);
        cars.removeIf(c -> c.license().equals(car.license()));
        notifyObservers();
    }
}
