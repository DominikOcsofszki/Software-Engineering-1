package parkhouse.config;

public class Config {

    private Config() {}
    public static final Double PRICE = 0.5;
    public static final Double FACTOR_PRICE_VIEW = 10000.;  //old     //price factor for showing on the website _do
    public static final String SIMULATION_SPEED = "100";
    public static int MAX_CARS = 11; // Maxmale Autos with getter/setter NOT final

    //_do
    public static int getMaxCars() {
        return MAX_CARS;
    }

    public static void setMaxCars(int maxCars) {
        MAX_CARS = maxCars;
    }
    //_do

}
