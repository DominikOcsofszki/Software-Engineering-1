package parkhouse.config;

public class Config {

    /*
    Author: TEAM
     */

    private Config() {}

    public static final int SIMULATION_SPEED = 100;
    public static final int TIME_SHIFT = 0;
    public static final int WAIT_REDLIGHT_SHIFT = 10;

    public static final String[] CLIENT_CATEGORIES = new String[] {"Default", "Women", "Business", "Family", "Disability"};
    public static final String[] VEHICLE_TYPES = new String[] {"PKW", "SUV", "QUAD", "TRIKE", "PICKUP"};
    public static final String[] PRICE_FACTOR = new String[] {"SUV:2", "Family:0.5", "Family.SUV:1.2"};

    public static final int DEFAULT_MAX_CARS = 16;
    public static final int DEFAULT_OPEN_FROM = 0;
    public static final int DEFAULT_OPEN_TO = 0;

}
