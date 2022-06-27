package parkhouse.servlets;

import parkhouse.calculations.Locator;
import parkhouse.calculations.Price;
import parkhouse.calculations.Stats;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.commands.CarEnterCommand;
import parkhouse.commands.CarLeaveCommand;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.security.SanitizedCar;
import parkhouse.util.Finder;
import parkhouse.util.Saver;
import parkhouse.util.Time;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class ParkhouseServlet extends HttpServlet {

    /*
    TODO: Author: mkaul2s & TEAM
     */

    abstract String name();

    abstract int max();

    abstract String config();

    private static final String RELOAD = "<img src='x' onerror=location.reload();>";

    private static final Logger LOGGER = Logger.getLogger(ParkhouseServlet.class.getName());

    /**
     * HTTP GET
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        LOGGER.info(cmd + "requested: " + request.getQueryString());

        switch (cmd) {
            case "config":
                out.println(config());
                if (Saver.init()) {
                    Saver.loadCars(parkingController(), name());
                    Runtime.getRuntime().addShutdownHook(new Thread(() ->
                        Saver.saveCars(parkingController(), name())
                    ));
                }
                break;
            case "cars":
                out.print(
                        parkingController().getAllCars()
                                .stream().map(c -> c.toString() + "/" + name())
                                .collect(Collectors.joining(","))
                );
                break;
            case "Sum":
                out.println(String.format(
                        "Total income: %s | (Live): %s",
                        Price.format(Stats.sumCars(parkingController().getRemovedCars())),
                        Price.format(Stats.sumCars(parkingController().getAllCars()))
                ));
                break;
            case "Avg":
                out.println(String.format(
                        "Average income per customer: %s | (Live): %s",
                        Price.format(Stats.avgCars(parkingController().getRemovedCars())),
                        Price.format(Stats.avgCars(parkingController().getAllCars()))
                ));
                break;
            case "Min":
                out.println(String.format(
                        "Lowest income from a customer: %s | (Live): %s",
                        Price.format(Stats.minCars(parkingController().getRemovedCars())),
                        Price.format(Stats.minCars(parkingController().getAllCars()))
                ));
                break;
            case "Max":
                out.println(String.format(
                        "Highest income from a customer: %s | (Live): %s",
                        Price.format(Stats.maxCars(parkingController().getRemovedCars())),
                        Price.format(Stats.maxCars(parkingController().getAllCars()))
                ));
                break;
            case "Types":
                out.println(
                        parkingController().vehicleTypeView()
                );
                break;
            case "Categories":
                out.println(
                        parkingController().clientCategoriesView()
                );
                break;
            case "Durations":
                out.println(
                        parkingController().durationView()
                );
                break;
            case "Daily-Earnings":
                out.println(
                        parkingController().dailyEarningsView()
                );
                break;
            case "Weekly-Earnings":
                out.println(
                        parkingController().weeklyEarningsView()
                );
                break;
            case "Current-Cost":
                out.println(
                        parkingController().currentCostView()
                );
                break;
            case "Earnings-Categories":
                out.println(
                        parkingController().earningsByCategoriesView()
                );
                break;
            case "Time":
                out.println(String.format(
                        "Real time: %d / Sim time: %d / Diff: %d",
                        Time.now(), Time.simNow(),
                        Time.simNow() - Time.now())
                );
                break;
            case "Reset":
                getServletContext().setAttribute("parkingController" + name(), null);
                out.println(RELOAD);
                break;
            case "Undo":
                parkingController().commander().undo();
                out.println(RELOAD);
                break;
            default:
                LOGGER.warning("Invalid Command: " + request.getQueryString());
        }
    }


    /**
     * HTTP POST
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = getBody(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        LOGGER.info(body);
        String[] params = body.split(",");
        String event = params[0];
        String[] restParams = Arrays.copyOfRange(params, 1, params.length);

        switch (event) {
            case "change_max":
                Config.setMaxCars(Integer.parseInt(restParams[0]));
                break;
            case "change_open_from":
                Config.setOpenFrom(Integer.parseInt(restParams[0]));
                break;
            case "change_open_to":
                Config.setOpenTo(Integer.parseInt(restParams[0]));
                break;
            case "enter":
                int space = Locator.locate(parkingController());
                if (space != -1) {
                    ICar car = new SanitizedCar(new Car(restParams));
                    car.setSpace(space);
                    CarEnterCommand cmd = new CarEnterCommand(car, parkingController());
                    parkingController().commander().queue(cmd);
                    parkingController().commander().activate();
                    out.println(space);
                }
                break;
            case "leave":
                ICar car = Finder.findCar(parkingController().getCars(), ICar::ticket, restParams[4]);
                car.updateParams(restParams);
                CarLeaveCommand cmd = new CarLeaveCommand(car, parkingController());
                parkingController().commander().queue(cmd);
                parkingController().commander().activate();
                out.println(restParams[3]);
                break;
            case "invalid":
            case "occupied":
                LOGGER.info(() -> "occupied: " + body);
                break;
            case "tomcat":
                out.println(getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion());
                break;

            default:
                LOGGER.info(() -> "Invalid Command: " + body);
        }

    }

    /**
     * @return the servlet context
     */
    ServletContext getContext() {
        return getServletConfig().getServletContext();
    }

    IParkingController parkingController() {
        if (getContext().getAttribute("parkingController" + name()) == null) {
            getContext().setAttribute("parkingController" + name(), new ParkingController());
        }
        return (IParkingController) getContext().getAttribute("parkingController" + name());
    }

    /**
     * @param request the HTTP POST request
     * @return the body of the request
     */
    String getBody(HttpServletRequest request) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try (InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void destroy() {
        LOGGER.info("Servlet destroyed");
    }
}