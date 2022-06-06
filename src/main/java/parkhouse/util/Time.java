package parkhouse.util;

import java.util.Date;

public class Time {

    private Time() {
        throw new IllegalStateException();
    }

    public static Date asDate(long timestamp) {
        return new Date(timestamp);
    }

}
