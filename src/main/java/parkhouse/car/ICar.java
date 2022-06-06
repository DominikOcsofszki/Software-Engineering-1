package parkhouse.car;

public interface ICar {
    int nr();
    int space();
    void setSpace(int x); // add new spaceNr for car, after locator
    long begin();
    long end();
    long duration();
    double price();
    String ticket();
    String color();
    String category();
    String type();
    String license();
    void updateParams(String[] params);
}
