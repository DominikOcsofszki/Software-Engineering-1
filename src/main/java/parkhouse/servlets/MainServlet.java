package parkhouse.servlets;

import parkhouse.config.Config;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends ParkhouseServlet {

    @Override
    String NAME(){
        return "MainServlet";
    }

    @Override
    int MAX(){
        return Config.maxCars;
    }

    @Override
    String config(){
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