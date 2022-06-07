package parkhouse.servlets;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Finder;
import parkhouse.util.Jsonify;
import parkhouse.util.Tableize;
import parkhouse.util.Time;

import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * common superclass for all parkhouse.servlets
 * groups all auxiliary common methods used in all parkhouse.servlets
 */
public abstract class ParkhouseServlet extends HttpServlet {

    /* abstract methods, to be defined in subclasses */
    abstract String NAME(); // each parkhouse.servlets.ParkhausServlet should have a name, e.g. "Level1"

    abstract int MAX(); // maximum number of parking slots of a single parking level

    abstract String config(); // configuration of a single parking level

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
//            case "start":
//                System.out.println("Time.asDate()");
            case "config":
                // Overwrite Parkhaus config parameters
                // Max, open_from, open_to, delay, simulation_speed
                System.out.println("Time.asDate()"); //ToDo Tobi
                Time.getTime();
                out.println(config());
                break;
            case "Sum":
                double sum = sumCars();
                out.println(String.format("Total income = %.2f€", sum));
//                out.println(sum);
                getContext().setAttribute("sum"+NAME(), sum);
                break;
            case "Avg":
                out.println(String.format("Average income per customer = %.2f€", avgCars()));
                break;
            case "Min":
                out.println(String.format("Lowest income from a customer = %.2f€", minCars()));
                break;
            case "Max":
                out.println(String.format("Highest income from a customer = %.2f€", maxCars()));
                break;
            case "cars":
                for (ICar c : cars()) {
                    out.println(String.format("%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                            c.nr(), c.begin(), c.duration(), c.price(), c.ticket(),
                            c.color(), c.space(), c.category(), c.type(), c.license()));
                }
                break;
            case "Types":
                JsonObject types = Jsonify.carsCount(cars(), ICar::type);
                out.println(
                        Jsonify.plot(
                                Jsonify.getKeys(types),
                                Jsonify.getValues(types),
                                "bar", "Types")
                );
                break;
            case "Categories":
                JsonObject categories = Jsonify.carsCount(cars(), ICar::category);
                out.println(
                        Jsonify.plot(
                                Jsonify.getKeys(categories),
                                Jsonify.getValues(categories),
                                "bar", "Categories")
                );
                break;
            case "Table":
                out.println(
                        Tableize.table(
                                new String[]{"Header 1", "Header 2"},
                                new String[][]{
                                        {"Data 1.1", "Data 1.2"},
                                        {"Data 2.1", "Data 2.2"}
                                }
                        )
                );
                break;
            case "Daily-Earnings":
                out.println(
                        parkingController().dailyEarningsView().getDailyEarnings()
                );
                break;
            case "Weekly-Earnings":
                out.println(
                        parkingController().weeklyEarningsView().getWeeklyEarnings()
                );
                break;
            case "Current-Cost":
                out.println(
                        parkingController().currentCostView()
                );
                break;
            case "Time":
                out.println(Time.getTime());
            case "Reset":
                getServletContext().setAttribute("Sum", 0);
                getServletContext().setAttribute("Avg", 0);
                getServletContext().setAttribute("Min", 0);
                getServletContext().setAttribute("Max", 0);
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
            //_do
            case "change_max":
                int x = Integer.parseInt(restParams[0]);
                Config.setMaxCars(x);
                System.out.println("change_max to:"+x);
            //
            case "enter":
                ICar newCar = new Car(restParams);
//                cars().add(newCar); //ToDo this one is needed since
                int spaceNr = locator(newCar);      //ToDO Not working fully yet
                if(spaceNr != -1) {
//                    cars().add(newCar); //ToDo Do not always add a Car, check first if enough space - Spot
                    // System.out.println( "enter," + newCar );
                    parkingController().addCar(newCar); // adding the car
//                    parkingController().addCar(restParams); // with params

                    // re-direct car to another parking lot
                    out.println(spaceNr);       //only do sth if space
                    System.out.println(newCar);
                }
//                out.println(spaceNr);
                break;
            case "leave":
//                ICar oldCar = cars().get(0);  // ToDo remove car from list
//                ICar oldCar = cars().remove(0); // Could rewove directly but is done in removeCar;
//                ICar oldCar = cars().remove(0); // Could rewove directly but is done in removeCar;
//                System.out.println("remove:"+oldCar);
//                System.out.println(restParams[4]);
//                ICar oldCar = Finder.findCar(getRemovedCarsController(), ICar::ticket, restParams[4]);
//                ICar oldCar = Finder.findCarByTicket(getRemovedCarsController(), ICar::ticket, restParams[4]);
//                System.out.println(getCarsController());
//                ICar streamOldCar = getCarsController().stream().
//                        filter(car -> (car.ticket().equals(restParams[4])))
//                                .findFirst().orElseThrow();
                ICar OldCar = ICarForTicket(restParams[4]);
//                System.out.println(streamOldCar);
//                parkingController().removeCar(restParams);
                // _do
//                oldCar.updateParams(restParams);
//                parkingController().removeCar(oldCar);
                OldCar.updateParams(restParams);
//                double price = streamOldCar.price();
                parkingController().removeCar(OldCar);


                double price = 0.0d;
        //ToDo how to get rid of this? Tried with price() and calcinCent but does not work!
                if (params.length > 4) {
                    String priceString = params[4];
                    if (!"_".equals(priceString)) {
                        price = (double) new Scanner(priceString).useDelimiter("\\D+").nextInt();
                        price /= 100.0d;  // just as Integer.parseInt( priceString ) / 100.0d;
                        // store new sum in ServletContext
                        // ToDo getContext().setAttribute("sum"+NAME(), getSum() + price );
//                        getContext().setAttribute("sum"+NAME(), getSum() + price );
                    }
                }
                out.println(price);  // server calculated price
//                System.out.println("leave," + oldCar + ", price = " + price);
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
                // System.out.println( "Invalid Command: " + body );
        }

    }

    //--------------------CALCULATIONS----------------------------------
    public double sumCars() {
        /*double ret = cars().stream().map(ICar::price). //Now working on ParkingModel
                filter(price -> (price > 0))
                .reduce(0d, Double::sum); */
        double ret = getRemovedCarsController().stream().map(ICar::price)
                .filter(price -> (price > 0))
                .reduce(0d, Double::sum);
        return calcInCent(ret);
    }

    public double calcInCent(double x) { // calc price to 0.01 Euro
        return x / Config.FACTOR_PRICE_VIEW;
    }

    public double avgCars() {
        long count = getRemovedCarsController().stream().filter(x -> (x.price() > 0)).count();
        if(count == 0) return 0;
        return sumCars() / count; // Hier unsicher ob sumCars verwendet werden sollte,
        // da sich sum verändern könnte, während count zuvor
        //nicht ganz sicher. Sollte copy erstellt werden?
    }

    public double minCars() {
        double ret = getRemovedCarsController().stream().mapToDouble(ICar::price).filter(x -> x > 0).min().orElseThrow(NoSuchElementException::new);
        return calcInCent(ret);
    }

    public double maxCars() {
        double ret = getRemovedCarsController().stream().mapToDouble(ICar::price).filter(x -> x > 0).max().orElseThrow(NoSuchElementException::new); //return the max price
//        return cars().stream().max(Comparator.comparing( ICar::price, Double::compareTo)).orElseThrow(NoSuchElementException::new); // returns the car
        return calcInCent(ret);
    }

//    public long plateParkingTime(String plateSearching) {
//        return cars().stream().filter(x -> (x.toString().equals(plateSearching))).map(ICar::duration).reduce(0L, Long::sum);
//    }
    public ICar ICarForTicket(String plateSearching) {

    ICar carTicket = getCarsController().stream().
            filter(car -> (car.ticket().equals(plateSearching)))
            .findFirst().orElseThrow();

        return carTicket;
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
    int locator(ICar car) {
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
            if(!set.contains(i)) {
//                car.setSpace(i);
                nr = i;
            }
        }
        System.out.println(set);
        System.out.println(set.size());
        System.out.println(nr);
        //ToDo find non existing Nr in that stream;
//        nr = 1 + ((cars().size() - 1) % this.MAX());
        car.setSpace(nr);
        return nr;
    }


    /**
     * @return the list of all cars stored in the servlet context so far
     */
    @SuppressWarnings("unchecked")
    List<ICar> cars() {
        if (getContext().getAttribute("cars" + NAME()) == null) {
            getContext().setAttribute("cars" + NAME(), new ArrayList<Car>());
        }
        return (List<ICar>) getContext().getAttribute("cars" + NAME());
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