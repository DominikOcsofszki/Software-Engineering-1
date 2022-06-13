package parkhouse.servlets;

import parkhouse.calculations.*;
import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Finder;
import parkhouse.util.Jsonify;
import parkhouse.util.Saver;
import parkhouse.util.Time;

import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * common superclass for all parkhouse.servlets
 * groups all auxiliary common methods used in all parkhouse.servlets
 */
public abstract class ParkhouseServlet extends HttpServlet {

    abstract String NAME();

    abstract int MAX();

    abstract String config();

    /**
     * HTTP GET
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        System.out.println(cmd + " requested: " + request.getQueryString());
        switch (cmd) {
            case "config":
                out.println(config());
//                Saver.outPutAfterReloadBool = true;
                if (Saver.outPutAfterReloadBool) {  //With if only happens one time
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        System.out.println("saving cars on 'server' or CSV data in java after shutdown.");
                        //ToDo add all cars from 'case: "cars"' into a txt or json.
                        Saver.shutdown(parkingController());    //Saves all cars into carsInHouse.txt and carsRemoved.txt
                    }));
                }
                break;
            case "Sum":
                out.println(String.format(Locale.US,
                        "Total income = %.2f€",
                        Price.out(new StatsSum().template1(parkingController())))
                );
                break;
            case "Avg":
                out.println(String.format(Locale.US,
                        "Average income per customer = %.2f€",
                        Price.out(new StatsAvg().template1(parkingController())))
                );
                break;
            case "Min":
                out.println(String.format(Locale.US,
                        "Lowest income from a customer = %.2f€",
                        Price.out(Stats.minCars(parkingController())))
                );
                break;
            case "Max":
                out.println(String.format(Locale.US,
                        "Highest income from a customer = %.2f€",
                        Price.out(Stats.maxCars(parkingController())))
                );
                break;
            case "cars":
//                Saver.outPutAfterReloadBool = true;
                if (Saver.outPutAfterReloadBool) {
                    Saver.outPutAfterServerReload(out, parkingController());  //ToDo could be done earlier -static onbject methode?
                    Saver.outPutAfterReloadBool = false;    //ToDo: Könnte später auch in der Methode geschehen

                    for (ICar c : parkingController().getCars()) {
                        out.println(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s,",
                                c.nr(), Time.realTime(c.begin()), "_", "_", c.ticket(),
                                c.color(), c.space(), c.category(), c.type(), c.license()));
                    }
                    for (ICar c : parkingController().getRemovedCars()) {
                        out.println(String.format(Locale.US, "%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                                c.nr(), Time.realTime(c.begin()), c.duration(), c.price(), c.ticket(),
                                c.color(), c.space(), c.category(), c.type(), c.license()));
                    }

                } else {

                    for (ICar c : parkingController().getCars()) {
                        out.println(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s,",
                                c.nr(), Time.realTime(c.begin()), "_", "_", c.ticket(),
                                c.color(), c.space(), c.category(), c.type(), c.license()));
                    }
                    for (ICar c : parkingController().getRemovedCars()) {
                        out.println(String.format(Locale.US, "%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                                c.nr(), Time.realTime(c.begin()), c.duration(), c.price(), c.ticket(),
                                c.color(), c.space(), c.category(), c.type(), c.license()));
                    }
                }
                break;


            case "Types":
                JsonObject types = Jsonify.carsCount(parkingController().getCars(), ICar::type);
                out.println(
                        Jsonify.plot(
                                Jsonify.getKeys(types),
                                Jsonify.getValues(types),
                                "bar", "Types")
                );
                break;
            case "Categories":
                JsonObject categories = Jsonify.carsCount(parkingController().getCars(), ICar::category);
                out.println(
                        Jsonify.plot(
                                Jsonify.getKeys(categories),
                                Jsonify.getValues(categories),
                                "bar", "Categories")
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
            case "Time":
                out.println(String.format(
                        "Real time: %d / Sim time: %d / Diff: %d",
                        Time.now(), Time.simNow(),
                        Time.simNow() - Time.now())
                );
            case "Reset":
                getServletContext().setAttribute("parkingController" + NAME(), null);
                //TODO Saver.reset()
                break;
            default:
                System.out.println("Invalid Command: " + request.getQueryString());
        }
    }


    /**
     * HTTP POST
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = getBody(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println(body);
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
                ICar newCar = new Car(restParams);
                int spaceNr = Locator.locate(parkingController());      //ToDo delete old Locator
                if (spaceNr != -1) {
                    newCar.setSpace(spaceNr);
                    parkingController().addCar(newCar); // adding the car
                    out.println(spaceNr);       //only do sth if space
                }
                break;
            case "leave":
                ICar oldCar = Finder.findICarForTicket(parkingController(), restParams[4]);
                double price = Price.priceFactDurationSimSpeed(oldCar);
                oldCar.leaveUpdatePriceDuration(String.valueOf(price));
                parkingController().removeCar(oldCar);

                out.println(Price.priceFactDurationSimSpeed(oldCar));
//                out.println(price); //ToDo use Variable price instead?
                break;
            case "invalid":
            case "occupied":
                System.out.println(body);
                break;
            case "tomcat":
                out.println(getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion());
                break;

            default:
                System.out.println(body);
        }

    }

    public List<ICar> getCarsController() {
        return parkingController().getCars();
    }

    public List<ICar> getRemovedCarsController() {
        return parkingController().getRemovedCars();
    }

    //-------------------------------------------------------

    // auxiliary methods used in HTTP request processing

    /**
     * @return the servlet context
     */
    ServletContext getContext() {
        return getServletConfig().getServletContext();
    }

    /**
     * TODO: replace this by your own function
     *
     * @return the number of the free parking lot to which the next incoming car will be directed
     */
    int locator(ICar car) {     //ToDo Delete since not used anymore!
        int nr = -1;
        // numbers of parking lots start at 1, not zero
        // return 1;  // always use the first space
//        int[] intStream = cars().stream().    // Gives all Nr. Spots used. Search for free one
//                        filter(x -> x.duration() == 0)
//                                        .mapToInt(ICar::space).sorted().toArray();
        int[] intStream = getCarsController().stream().    // Gives all Nr. Spots used. Search for free one
                filter(x -> x.duration() == 0)
                .mapToInt(ICar::space).sorted().toArray();
        Set<Integer> set = new HashSet<>();
        for (int x : intStream
        ) {
            set.add(x);
        }
        for (int i = 0; i <= MAX(); i++) {
            if (!set.contains(i)) {
//                car.setSpace(i);
                nr = i;
            }
        }
//        System.out.println(set);
//        System.out.println(set.size());
//        System.out.println(nr);
        //ToDo find non existing Nr in that stream;
//        nr = 1 + ((cars().size() - 1) % this.MAX());
        car.setSpace(nr);
        return nr;
    }

    IParkingController parkingController() {
        if (getContext().getAttribute("parkingController" + NAME()) == null) {
            getContext().setAttribute("parkingController" + NAME(), new ParkingController());
        }
        return (IParkingController) getContext().getAttribute("parkingController" + NAME());
    }

    /**
     * @param request the HTTP POST request
     * @return the body of the request
     */
    String getBody(HttpServletRequest request) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
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
        System.out.println("Servlet destroyed.");
    }
}