public class Time implements TimeIF {

    private int hours;
    private int minutes;
    private int seconds;

    public Time() {
        this(0, 0, 0);
    }

    public Time(int h, int m, int s) {
        this.setHours(h);
        this.setMinutes(m);
        this.setSeconds(s);
    }

    public Time(String t) {
        String[] time = t.split(":");
        if (time.length != 3) {
            throw new IllegalArgumentException("Invalid format");
        }
        try {
            this.setHours(Integer.parseInt(time[0]));
            this.setMinutes(Integer.parseInt(time[1]));
            this.setSeconds(Integer.parseInt(time[2]));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    public void add(TimeIF o) {
        this.addHours(o.getHours());
        this.addMinutes(o.getMinutes());
        this.addSeconds(o.getSeconds());
    }

    public void sub(TimeIF o) {
        this.subHours(o.getHours());
        this.subMinutes(o.getMinutes());
        this.subSeconds(o.getSeconds());
    }

    public TimeIF diff(TimeIF o) {
        TimeIF t = new Time();
        t.setHours(Math.abs(this.hours - o.getHours()));
        t.setMinutes(Math.abs(this.minutes - o.getMinutes()));
        t.setSeconds(Math.abs(this.seconds - o.getSeconds()));
        return t;
    }

    public void addHours(int h) {
        this.setHours(this.hours + h);
    }

    public void subHours(int h) {
        this.setHours(this.hours - h);
    }

    public void addMinutes(int m) {
        this.setMinutes(this.minutes + m);
    }

    public void subMinutes(int m) {
        this.setMinutes(this.minutes - m);
    }

    public void addSeconds(int s) {
        this.setSeconds(this.seconds + s);
    }

    public void subSeconds(int s) {
        this.setSeconds(this.seconds - s);
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int h) {
        this.hours = mod(h, 24);
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int m) {
        this.minutes = mod(m, 60);
        if (m < 0 && m > -60) {
            m = -60;
        }
        this.addHours(m / 60);
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int s) {
        this.seconds = mod(s, 60);
        if (s < 0 && s > -60) {
            s = -60;
        }
        this.addMinutes(s / 60);
    }

    @Override
    public int compareTo(TimeIF o) {
        if (this.hours > o.getHours()) return 1;
        if (this.hours == o.getHours() && this.minutes > o.getMinutes()) return 1;
        if (this.hours == o.getHours() && this.minutes == o.getMinutes() && this.seconds > o.getSeconds()) return 1;
        if (this.hours == o.getHours() && this.minutes == o.getMinutes() && this.seconds == o.getSeconds()) return 0;
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof TimeIF)) return false;
        return this.compareTo((TimeIF) obj) == 0;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", this.hours, this.minutes, this.seconds);
    }

    private int mod(int i, int m) {
        int r = i % m;
        if (r < 0) {
            r += m;
        }
        return r;
    }
}
