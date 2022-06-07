package parkhouse.car;

import java.util.Arrays;

public class Car implements ICar {

    String[] params;

    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return Integer.parseInt(params[0]);
    }

    @Override
    public int space() {
        return Integer.parseInt(params[6]);
    }

    @Override
    public void setSpace(int x) { // add new spaceNr for car, after locator
        params[6] = x+"";
    }

    @Override
    public long begin() {
        return Long.parseLong(params[1]);
    }

    @Override
    public long end() {
        return this.begin() + this.duration();      //ToDo duration() => Zeit*Sim_Fact ->Somit keine echte Zeit
    }

    @Override
    public long duration() {
        if (params[2].equals("_")) {
            return 0;
        }
        return Long.parseLong(params[2]);
    }

    @Override
    public double price() {
        if(duration() == 0) return 0;  // if the car did not leave yet, return as price = 0
        //ToDo _do: Jakob? use this methode for price changes? Input factors here?
        return Double.parseDouble(params[3]);
    }

    @Override
    public String ticket() {
        return params[4];
    }

    @Override
    public String color() {
        return params[5];
    }

    @Override
    public String category() {
        return params[7];
    }

    @Override
    public String type() {
        return params[8];
    }

    @Override
    public String license() {
        return params[9];
    }

    @Override
    public void updateParams(String[] params) {
        this.params = params;
    }

    @Override
    public String toString(){
        return Arrays.toString( params );
    }
}
