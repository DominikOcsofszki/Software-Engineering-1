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
    void updateParams(String[] params);
}
