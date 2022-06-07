package parkhouse.servlets;

import parkhouse.config.Config;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends ParkhouseServlet {

    @Override
    String NAME(){
        return "Park house";
    }

    @Override
    int MAX(){ // maximum number of parking slots on level 1
        return Config.getMaxCars();
    }

    @Override
    String config(){
//        return ""; // use default config
        return this.MAX() + ",0,24,"+ Config.SIMULATION_SPEED+",10";     //use MAX() for calculating parking spots _do
    }



}