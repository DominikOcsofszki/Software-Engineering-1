package parkhouse;

public interface CarIF {
    int nr();
    long begin();
    long end();
    long duration();
    double price();
    void updateParams(String[] params); // Update params, added by Dominik 26.04
}
