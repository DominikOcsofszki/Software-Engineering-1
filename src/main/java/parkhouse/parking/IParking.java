package parkhouse.parking;

import parkhouse.car.ICar;

public interface IParking {

    void enter(ICar car);
    void leave();

}
