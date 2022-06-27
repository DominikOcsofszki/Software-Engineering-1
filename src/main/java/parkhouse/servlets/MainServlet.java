package parkhouse.servlets;

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
        return config[0];
    }

}
