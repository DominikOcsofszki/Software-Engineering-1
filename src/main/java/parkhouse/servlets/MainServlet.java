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
    int MAX(){
        return Config.maxCars;
    }

    @Override
    String config(){
//        return this.MAX() + ",0,24,"+ Config.SIMULATION_SPEED+",10";     //use MAX() for calculating parking spots _do ToDo Laut API anders als hier! Eione Zahl fehlt.
        return String.format(
                "%d,%d,%d,%d,%d,%d",
                Config.maxCars,
                Config.openFrom,
                Config.openTo,
                Config.SIMULATION_SPEED,
                Config.WAIT_REDLIGHT_SHIFT,
                Config.TIME_SHIFT
        );

    }                   //20,6,24,100,1234567890, 10
    /*
    2.1.1. Serverseitige Konfiguration des Parkhaus-Clients

Beim Laden des Parkhaus-Clients sendet dieser folgenden HTTP-GET-Request an den Server:

    http://Server/ServletRoute?cmd=config&name=ParkhausName

Das Servlet unter der Route ServletRoute auf dem Server mit dem Namen Server wird damit aufgefordert, die Konfigurationsparameter für das Parkhaus mit dem Namen ParkhausName an den Client zu senden. (Bitte ersetzen Sie darin Ihre eigenen Server- und Servlet-Namen.)

HTTP-Response ist daraufhin z.B.

    20,6,24,100,1234567890, 10

Die Konfigurationsparameter darin sind:

    Maximale Anzahl der Autos im Parkhaus (20)
    Öffnungszeit (um 6:00 Uhr)
    Schließzeit (um 24:00 Uhr)
    Verzögerung beim Umschalten der Ampel in Millisekunden (100 msec)
    time_shift (Verschiebung der Simulationszeit in die Vergangenheit um 1234567890 Millisekunden, s.o.)
    simulation_speed (10) (Faktor der Beschleunigung der Simulationszeit gegenüber der Realzeit)
    */


}