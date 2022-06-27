package parkhouse.servlets;

import parkhouse.config.Config;
import parkhouse.util.Saver;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends ParkhouseServlet {

    /*
    Author: TEAM
     */

    @Override
    String name() {
        return "MainServlet";
    }

    @Override
    int max() {
        return Config.maxCars;
    }

    @Override
    String config() {
        if (Saver.initConfig()) Saver.loadConfig(name());
        return String.format(
                "%d,%d,%d,%d,%d,%d",
                Config.maxCars,
                Config.openFrom,
                Config.openTo,
                Config.SIMULATION_SPEED,
                Config.WAIT_REDLIGHT_SHIFT,
                Config.TIME_SHIFT
        );
    }

}
