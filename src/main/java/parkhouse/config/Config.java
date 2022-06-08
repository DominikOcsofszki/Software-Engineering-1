package parkhouse.config;

public class Config {

    private Config() {}
    public static final int SIMULATION_SPEED = 100;
    public static final int TIME_SHIFT = 0; //ToDo needed? //time_shift (Verschiebung der Simulationszeit in die Vergangenheit um 1234567890 Millisekunden)
    public static final int WAIT_REDLIGHT_SHIFT = 10;        //ToDo needed? //

    public static final String[] CLIENT_CATEGORIES = new String[] {"Default", "Women", "Business", "Family", "Disability"};
    public static final String[] VEHICLE_TYPES = new String[] {"PKW", "SUV"};
    public static final String[] PRICE_FACTOR = new String[] {"SUV:2", "Women:0.5"};

    public static int MAX_CARS = 11;
    public static int OPEN_FROM = 0;
    public static int OPEN_TO = 0;

    public static void setMaxCars(int maxCars) {
        MAX_CARS = maxCars;
    }
    public static void setOpenFrom(int openFrom) {
        OPEN_FROM = openFrom;
    }
    public static void setOpenTo(int openTo) {
        OPEN_TO = openTo;
    }

}
