package parkhouse.car;

public interface CarIF {
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
    void updateParams(String[] params);
}
