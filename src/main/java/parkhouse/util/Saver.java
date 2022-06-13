package parkhouse.util;

import parkhouse.car.Car;
import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Saver {

    private Saver() {    }

    //------------
    public static boolean outPutAfterReloadBool = true;     //ToDo better methode since not final?
    public static boolean inSaverDebug = true;     //ToDo better methode since not final?
    //--------------


    public static void shutdown(IParkingController parkingController) {     //ToDo Change into Json Format?
        try {

            String fileName = "carsInHouse.txt";
            FileWriter fw = new FileWriter(fileName);
//            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String fileNameRemoved = "carsRemoved.txt";
            FileWriter fw2 = new FileWriter(fileNameRemoved);
//            FileWriter fw2 = new FileWriter(fileNameRemoved, true);
            BufferedWriter bwRemoved = new BufferedWriter(fw2);

//            bw.write("firs test");
            formatOutPutShutdown(parkingController, bw, bwRemoved);
            bw.newLine();
            bwRemoved.newLine();
            bw.close();
            bwRemoved.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void formatOutPutShutdown(IParkingController parkingController, BufferedWriter bw, BufferedWriter bwRemoved) throws IOException {
        for (ICar c : parkingController.getCars()) {
            bw.write(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s/%s\n",      // \n was added ","deleted
//            bw.write(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s,\n",      // \n was added
                    c.nr(), Time.realTime(c.begin()), "_", "_", c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license(),c.lastParameter()));
        }
        for (ICar c : parkingController.getRemovedCars()) {
            bwRemoved.write(String.format(Locale.US,"%d/%d/%d/%f/%s/%s/%d/%s/%s/%s/%s\n",  //\n was added "," deleted
//            bwRemoved.write(String.format(Locale.US,"%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,\n",  //\n was added
                    c.nr(), Time.realTime(c.begin()), c.duration(), c.price(), c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license(), c.lastParameter()));
        }
    }
    public static void outPutAfterServerReload(PrintWriter out, IParkingController parkingController)  {        //ToDo in this way in JS - sometimes double -BUT not yet in java -> Cars: car.updateParams(Formating (scInside.nextLine()))
        try {                                                       //ToDo making Cars out of the file. Better way might be with Json
            FileInputStream carInside = new FileInputStream("carsInHouse.txt");
            Scanner scInside = new Scanner(carInside);

            FileInputStream carRemoved = new FileInputStream("carsRemoved.txt");
            Scanner scRemoved = new Scanner(carRemoved);


            while(scInside.hasNextLine()){
                String sInside = scInside.nextLine();
                if(sInside.equals("")) continue;    //skip empty lines
                String[] params = importCarsFormater(sInside);
//                System.out.println(params);

                parkingController.addCarRestartServer(new Car(params));
                out.print(sInside);      //returns the line that was skipped
//                out.print(scInside.nextLine());      //returns the line that was skipped
            }
            while(scRemoved.hasNextLine()){
                String sRemoved = scRemoved.nextLine();
                if(sRemoved.equals("")) continue;   //skip empty lines
                String[] params = importCarsFormater(sRemoved);
//                System.out.println(params);
                parkingController.addRemovedCarRestartServer(new Car(params));
                out.print(sRemoved);      //returns the line that was skipped
//                out.print(scRemoved.nextLine());      //returns the line that was skipped
            }
            scInside.close();
            scRemoved.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String[] importCarsFormater(String stringFromTxt) {
        int maxParams = 10;
//        System.out.println("stringFromTxt:"+stringFromTxt);
        String[] params = stringFromTxt.split("\t*/\t*");
        String[] last = params[maxParams].split(",");
        params[maxParams] = last[0];
        String[] lengthEndNoCommaParams = Arrays.copyOfRange(params, 0, maxParams + 1);
//        System.out.println("stringFromTxt.Params:" + Arrays.toString(params));
//        System.out.println("lengthEndNoCommaParams:" + Arrays.toString(lengthEndNoCommaParams));
        return lengthEndNoCommaParams;
//        return params;


//        parkingController.getCars();
//        parkingController.getRemovedCars();

    }

}
