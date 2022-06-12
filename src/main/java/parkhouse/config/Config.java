package parkhouse.config;

public class Config {

    private enum ConfigMode {
        DEBUG_MODE, NORMAL_MODE
    }

    private Config() {}
    //New for debuging Ausgabe
    public static ConfigMode configMode = ConfigMode.DEBUG_MODE;

    public static final int SIMULATION_SPEED = 100;
    public static final int TIME_SHIFT = 0;
    public static final int WAIT_REDLIGHT_SHIFT = 10;

    public static final String[] CLIENT_CATEGORIES = new String[] {"Default", "Women", "Business", "Family", "Disability"};
    public static final String[] VEHICLE_TYPES = new String[] {"PKW", "SUV"};
    public static final String[] PRICE_FACTOR = new String[] {"SUV:2", "Women:0.5"};

    public static int maxCars = 11;
    public static int openFrom = 0;
    public static int openTo = 0;

    public static void setMaxCars(int maxCars) {
        Config.maxCars = maxCars;
    }
    public static void setOpenFrom(int openFrom) {
        Config.openFrom = openFrom;
    }
    public static void setOpenTo(int openTo) {
        Config.openTo = openTo;
    }

    public static boolean isConfigDebugMode() {
        return configMode == ConfigMode.DEBUG_MODE;
    }
    public static boolean isConfigNormalMode() {
        return configMode == ConfigMode.NORMAL_MODE;
    }

}
