package parkhouse.util;

import java.util.Date;

public abstract class Time {

    public static Date asDate(long timestamp) {
        return new Date(timestamp);
    }


}
