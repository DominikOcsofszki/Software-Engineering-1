package parkhouse.servlets;

import parkhouse.car.Car;
import parkhouse.car.CarIF;
import parkhouse.util.Finder;
import parkhouse.util.Jsonify;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * common superclass for all parkhouse.servlets
 * groups all auxiliary common methods used in all parkhouse.servlets
 */
public abstract class ParkhausServlet extends HttpServlet {

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
        System.out.println( cmd + " requested: " + request.getQueryString());
        switch ( cmd ){
            case "config":
                // Overwrite Parkhaus config parameters
                // Max, open_from, open_to, delay, simulation_speed
                out.println( config() );
                break;
            case "sum":
                // ToDo: insert algorithm for calculating sum here
                out.println( "sum = server side calculated sum" );
                break;
            case "avg":
                // ToDo
                break;
            case "min":
                // ToDo: insert algorithm for calculating min here
                out.println( "min = server side calculated min" );
                break;
            case "max":
                // ToDo: insert algorithm for calculating max here
                out.println( "max = server side calculated max" );
                break;
            case "cars":
                for (CarIF c : cars()) {
                    out.println(String.format("%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                            c.nr(), c.begin(), c.duration(), c.price(), c.ticket(),
                            c.color(), c.space(), c.category(), c.type(), c.license()));
                }
                break;
            case "chart":
                JsonObject root = Json.createObjectBuilder()
                    .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                            .add("x", Jsonify.carsAsNr(cars()))
                            .add("y", Jsonify.carsAsDuration(cars()))
                            .add("type", "bar")
                            .add("name", "Duration")
                )).build();
                out.println(root.toString());
                break;
            default:
                System.out.println("Invalid Command: " + request.getQueryString());
        }
    }

    /**
     * HTTP POST
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String body = getBody( request );
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println( body );
        String[] params = body.split(",");
        String event = params[0];
        String[] restParams = Arrays.copyOfRange(params, 1, params.length);

        switch( event ){
            case "enter":
                CarIF newCar = new Car( restParams );
                cars().add( newCar );
                // System.out.println( "enter," + newCar );

                // re-direct car to another parking lot
                out.println( locator( newCar ) );
                break;
            case "leave":
                CarIF oldCar = cars().get(0);  // ToDo remove car from list

                getCarByNr(Integer.parseInt(restParams[0])).updateParams(restParams);

                double price = 0.0d;
                if ( params.length > 4 ){
                    String priceString = params[4];
                    if ( ! "_".equals( priceString ) ){
                        price = (double)new Scanner( priceString ).useDelimiter("\\D+").nextInt();
                        price /= 100.0d;  // just as Integer.parseInt( priceString ) / 100.0d;
                        // store new sum in ServletContext
                        // ToDo getContext().setAttribute("sum"+NAME(), getSum() + price );
                    }
                }
                out.println( price );  // server calculated price
                System.out.println( "leave," + oldCar + ", price = " + price );
                break;
            case "invalid": case "occupied":
                System.out.println( body );
                break;
            case "tomcat":
                out.println( getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion() );
                break;
            default:
                System.out.println( body );
                // System.out.println( "Invalid Command: " + body );
        }

    }


    // auxiliary methods used in HTTP request processing

    /**
     * @return the servlet context
     */
    ServletContext getContext(){
        return getServletConfig().getServletContext();
    }

    /**
     * TODO: replace this by your own function
     * @return the number of the free parking lot to which the next incoming car will be directed
     */
    int locator( CarIF car ){
        // numbers of parking lots start at 1, not zero
        // return 1;  // always use the first space
        return 1 + (( cars().size() - 1 ) % this.MAX());
    }

    /**
     * @return the list of all cars stored in the servlet context so far
     */
    @SuppressWarnings("unchecked")
    List<CarIF> cars(){
        if ( getContext().getAttribute( "cars"+NAME() ) == null ){
            getContext().setAttribute( "cars"+NAME(), new ArrayList<Car>() );
        }
        return (List<CarIF>) getContext().getAttribute( "cars"+NAME() );
    }

    CarIF getCarByNr(int nr) {
        for (CarIF c : cars()) {
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
    String getBody( HttpServletRequest request ) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if ( inputStream != null ) {
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