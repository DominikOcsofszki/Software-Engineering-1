package parkhouse.util;

import parkhouse.car.ICar;
import parkhouse.controller.IParkingController;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Saver {

    private Saver() {    }

    //------------
    public static boolean outPutAfterReloadBool = true;     //ToDo better methode since not final?
    //--------------


    public static void shutdown(IParkingController parkingController) {     //ToDo Change into Json Format?
        try {

            String fileName = "carsInHouse.txt";
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String fileNameRemoved = "carsRemoved.txt";
            FileWriter fw2 = new FileWriter(fileNameRemoved, true);
            BufferedWriter bwRemoved = new BufferedWriter(fw2);

//            bw.write("firs test");
            formatOutPut(parkingController, bw, bwRemoved);
            bw.newLine();
            bwRemoved.newLine();
            bw.close();
            bwRemoved.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void formatOutPut(IParkingController parkingController, BufferedWriter bw, BufferedWriter bwRemoved) throws IOException {
        for (ICar c : parkingController.getCars()) {
            bw.write(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s,\n",      // \n was added
                    c.nr(), Time.realTime(c.begin()), "_", "_", c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license()));
        }
        for (ICar c : parkingController.getRemovedCars()) {
            bwRemoved.write(String.format(Locale.US,"%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,\n",  //\n was added
                    c.nr(), Time.realTime(c.begin()), c.duration(), c.price(), c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license()));
        }
    }
    public static void outPutAfterReload(PrintWriter out)  {        //ToDo in this way in JS - sometimes double -BUT not yet in java -> Cars: car.updateParams(Formating (scInside.nextLine()))
        try {                                                       //ToDo making Cars out of the file. Better way might be with Json
            FileInputStream carInside = new FileInputStream("carsInHouse.txt");
            Scanner scInside = new Scanner(carInside);

            FileInputStream carRemoved = new FileInputStream("carsRemoved.txt");
            Scanner scRemoved = new Scanner(carRemoved);


            while(scInside.hasNextLine()){
                out.print(scInside.nextLine());      //returns the line that was skipped
            }
            while(scRemoved.hasNextLine()){
                out.print(scRemoved.nextLine());      //returns the line that was skipped
            }
            scInside.close();
            scRemoved.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


 /*   private static void formatOutPut(IParkingController parkingController, BufferedWriter bw) {
        for (ICar c : parkingController.getCars()) {
            out.println(String.format("%d/%d/%s/%s/%s/%s/%d/%s/%s/%s,",
                    c.nr(), Time.realTime(c.begin()), "_", "_", c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license()));
        }
        for (ICar c : parkingController.getRemovedCars()) {
            out.println(String.format(Locale.US,"%d/%d/%d/%f/%s/%s/%d/%s/%s/%s,",
                    c.nr(), Time.realTime(c.begin()), c.duration(), c.price(), c.ticket(),
                    c.color(), c.space(), c.category(), c.type(), c.license()));
        }
    }*/