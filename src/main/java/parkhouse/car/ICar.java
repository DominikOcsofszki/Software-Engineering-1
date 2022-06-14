package parkhouse.car;

public interface ICar {
    int nr();
    int space();
    long timer();
    long begin();
    long end();
    long duration();
    long price();
    String ticket();
    String color();
    String category();
    String type();
    String license();
    void setSpace(int x);
    void leaveUpdatePriceDuration(String price);        //Changed name for better understanding.
    void updateParams(String[] params);
}
