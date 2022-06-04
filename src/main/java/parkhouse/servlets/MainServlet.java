package parkhouse.servlets;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends ParkhouseServlet {

    @Override
    String NAME(){
        return "Park house";
    }

    @Override
    int MAX(){ // maximum number of parking slots on level 1
        return 11;
    }

    @Override
    String config(){
        return ""; // use default config
        // Config Format is "Max, open_from, open_to, delay, simulation_speed"
        // e.g. return this.MAX() + ",5,23,100,10";  // TODO replace by your own parameters
    }



}