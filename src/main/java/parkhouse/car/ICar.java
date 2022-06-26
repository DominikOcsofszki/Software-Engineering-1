package parkhouse.car;

public interface ICar {
    boolean subscrition();
    void setSubscrition(long x);
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
    boolean gone();
    void setSpace(int s);
    void updateParams(String[] params);
}
