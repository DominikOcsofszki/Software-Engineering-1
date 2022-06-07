package parkhouse.config;

public class Config {

    private Config() {}
    //public static final Double PRICE = 0.5;
    //public static final Double FACTOR_PRICE_VIEW = 10000.;  //old     //price factor for showing on the website _do
    public static final String SIMULATION_SPEED = "100";
    public static final String[] CLIENT_CATEGORIES = new String[] {"Default", "Women", "Business", "Family", "Disability"};
    public static final String[] VEHICLE_TYPES = new String[] {"PKW", "SUV"};
    public static final String[] PRICE_FACTOR = new String[] {"SUV:2", "Women:0.5"};

    public static int MAX_CARS = 11; // Maxmale Autos with getter/setter NOT final


    public static void setMaxCars(int maxCars) {
        MAX_CARS = maxCars;
    }
    //_do

}
