package parkhouse.car;

public interface ICar {
    int nr();
    int space();
    long begin();
    long end();
    long duration();
    double price();
    String ticket();
    String color();
    String category();
    String type();
    String license();
    void setSpace(int x);
    void leaveUpdatePriceDuration(String price);        //Changed name for better understanding.
    void updateParams(String[] params);
    //--
    String lastParameter();
}
