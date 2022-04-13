import java.util.ArrayList;

public interface ParkTimerIF {

    void registerVehicle(String license, TimeIF t);   // register a vehicle upon entering
    float calculateTotal(String license, TimeIF t);   // calculate a total depending on parking time

    void setPrice(float price);                       // set a price per minute
    float getPrice();                                 // get the currently set price

    ArrayList<String[]> getRegistered();              // get list of registered vehicles
}
