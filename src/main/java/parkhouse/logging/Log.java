package parkhouse.logging;

import java.util.logging.*;

public class Log {

    private static final Log INSTANCE = new Log();
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Log() {
        logger.setLevel(Level.INFO);
        logger.info("Logger initialized!");
    }

    public static Logger getLogger() {
        return INSTANCE.logger;
    }

}
