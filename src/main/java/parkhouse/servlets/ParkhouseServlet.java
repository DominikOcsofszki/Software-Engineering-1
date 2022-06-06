package parkhouse.servlets;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.config.Config;
import parkhouse.controller.IParkingController;
import parkhouse.controller.ParkingController;
import parkhouse.util.Finder;
import parkhouse.util.Jsonify;
import parkhouse.util.Tableize;

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
            case "config":
                // Overwrite Parkhaus config parameters
                // Max, open_from, open_to, delay, simulation_speed
                out.println(config());
                break;
            case "Sum":
                double sum = sumCars();
                out.println("sum =" + sum);
                getContext().setAttribute("sum"+NAME(), sum);

                break;
            case "Avg":
                out.println("avg = " + avgCars());
                break;
            case "Min":
                out.println("min = " + minCars());
                break;
            case "Max":
                out.println("max = " + maxCars());
                break;
            case "cars":
                for (ICar c : cars()) {
                    out.println(String.format("%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                            c.nr(), c.begin(), c.duration(), c.price(), c.ticket(),
                            c.color(), c.space(), c.category(), c.type(), c.license()));
                }
                break;
            case "Chart":
                out.println(
                        Jsonify.plot(
                                Jsonify.carsAsNr(cars()),
                                Jsonify.carsAsDuration(cars()),
                                "bar", "Duration")
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
            case "enter":
                ICar newCar = new Car(restParams);
                cars().add(newCar); //ToDo Do not always add a Car, check first if enough space - Spot
                // System.out.println( "enter," + newCar );

                parkingController().addCar(restParams);

                // re-direct car to another parking lot
                out.println(locator(newCar));       //ToDo Understand -> gives Nr to JS, thereby pics correct?
                                                    //ToDo locator finds empty spot, but the old number is still saved.
                break;
            case "leave":
                ICar oldCar = cars().get(0);  // ToDo remove car from list

                //getCarByNr(Integer.parseInt(restParams[0])).updateParams(restParams);
                Finder.findCar(cars(), ICar::ticket, restParams[4]);

                parkingController().removeCar(restParams);

                double price = 0.0d;
                if (params.length > 4) {
                    String priceString = params[4];
                    if (!"_".equals(priceString)) {
                        price = (double) new Scanner(priceString).useDelimiter("\\D+").nextInt();
                        price /= 100.0d;  // just as Integer.parseInt( priceString ) / 100.0d;
                        // store new sum in ServletContext
                        // ToDo getContext().setAttribute("sum"+NAME(), getSum() + price );
                    }
                }
                out.println(price);  // server calculated price
                System.out.println("leave," + oldCar + ", price = " + price);
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

    //------------------------------------------------------
    public double sumCars() {
        double ret = cars().stream().map(ICar::price).
                filter(price -> (price > 0))
                .reduce(0d, Double::sum);
        return calcInCent(ret);
    }

    public double calcInCent(double x) { // calc price to 0.01 Euro
        return x / Config.FACTOR_PRICE_VIEW;
    }

    public double avgCars() {
        long count = cars().stream().filter(x -> (x.price() > 0)).count();
        return sumCars() / count; // Hier unsicher ob sumCars verwendet werden sollte,
        // da sich sum verändern könnte, während count zuvor
        //nicht ganz sicher. Sollte copy erstellt werden?
    }

    public double minCars() {
        double ret = cars().stream().mapToDouble(ICar::price).filter(x -> x > 0).min().orElseThrow(NoSuchElementException::new);
        return calcInCent(ret);
    }

    public double maxCars() {
        double ret = cars().stream().mapToDouble(ICar::price).filter(x -> x > 0).max().orElseThrow(NoSuchElementException::new); //return the max price
//        return cars().stream().max(Comparator.comparing( ICar::price, Double::compareTo)).orElseThrow(NoSuchElementException::new); // returns the car
        return calcInCent(ret);
    }

    public long plateParkingTime(String plateSearching) {
        return cars().stream().filter(x -> (x.toString().equals(plateSearching))).map(ICar::duration).reduce(0L, Long::sum);
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
        // numbers of parking lots start at 1, not zero
        // return 1;  // always use the first space
        int[] intStream = cars().stream().    // Gives all Nr. Spots used. Search for free one
                        filter(x -> x.duration() == 0)
                                        .mapToInt(ICar::space).sorted().toArray();
        Set<Integer> set = new HashSet<>();
        for (int x : intStream
             ) {
            set.add(x);
        }
        for (int i = 0; i < MAX(); i++) {
            if(!set.contains(i)) return i;
        }
        System.out.println(set);
        System.out.println(set.size());
        //ToDo find non existing Nr in that stream;
        return 1 + ((cars().size() - 1) % this.MAX());
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

    ICar getCarByNr(int nr) {
        for (ICar c : cars()) {
            if (c.nr() == nr) {
                return c;
            }
        }
        return null;
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