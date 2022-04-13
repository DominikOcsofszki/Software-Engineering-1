public interface TimeIF extends Comparable<TimeIF> {

    void add(TimeIF o);         // add time
    void sub(TimeIF o);         // subtract time

    TimeIF diff(TimeIF o);      // difference between times

    void addHours(int h);       // add hours
    void subHours(int h);       // subtract hours
    void addMinutes(int m);     // add minutes
    void subMinutes(int m);     // subtract minutes
    void addSeconds(int s);     // add seconds
    void subSeconds(int s);     // subtract seconds

    int getHours();             // get hours
    void setHours(int h);       // set hours
    int getMinutes();           // get minutes
    void setMinutes(int m);     // set minutes
    int getSeconds();           // get seconds
    void setSeconds(int s);     // set seconds

}
