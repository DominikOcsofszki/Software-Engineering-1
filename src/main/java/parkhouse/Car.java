package parkhouse;

import java.util.Arrays;

public class Car implements CarIF {
    String[] params;
    public Car( String[] params ){
        this.params = params;
    }

    @Override
    public int nr() {
        return Integer.parseInt(params[1]);
    }

    @Override
    public long begin() {
        return Long.parseLong(params[2]);
    }

    @Override
    public long end() {
        return this.begin() + this.duration();
    }

    @Override
    public int duration() {
        return Integer.parseInt(params[3]);
    }

    @Override
    public int price() {
        return Integer.parseInt(params[4]);
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
