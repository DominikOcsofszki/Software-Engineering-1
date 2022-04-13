import java.util.ArrayList;

public interface ParkTimerIF {

    void registerVehicle(String license, TimeIF t);   // register a vehicle upon entering
    void unregisterVehicle(String license);             // unregister a vehicle
    float calculateTotal(String license, TimeIF t);   // calculate a total depending on parking time

    void setPrice(float price);                       // set a price per minute
    float getPrice();                                 // get the currently set price

    ArrayList<String[]> getRegistered();              // get list of registered vehicles
}
