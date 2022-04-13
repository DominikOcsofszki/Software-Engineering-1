import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ParkTimer implements ParkTimerIF {

    private final ArrayList<String[]> registered;
    private float price;

    public ParkTimer() {
        registered = new ArrayList<>();
        price = 0;
    }

    public void registerVehicle(String license, TimeIF t) {
        String[] r = {license, t.toString()};
        registered.add(r);
    }

    @Override
    public void registerVehicle(String license) {

    }

    public float calculateTotal(String license, TimeIF t) {
        TimeIF p = getTime(license);
        if (p == null) {
            throw new NoSuchElementException();
        }
        TimeIF d = t.diff(p);
        return getMinutes(d) * price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }

    public ArrayList<String[]> getRegistered() {
        return registered;
    }

    private int getMinutes(TimeIF t) {
        int m = 0;
        m += t.getHours() * 60;
        m += t.getMinutes();
        return m;
    }

    private TimeIF getTime(String license) {
        TimeIF t = null;
        for (String[] strings : registered) {
            if (strings[0].equals(license)) {
                t = new Time(strings[1]);
            }
        }
        return t;
    }
}
