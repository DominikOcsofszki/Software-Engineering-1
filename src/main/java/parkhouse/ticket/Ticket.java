package parkhouse.ticket;

public class Ticket implements ITicket {

    private final String id;
    private final String license;
    private final long begin;

    public Ticket(String id, String license, long begin) {
        this.id = id;
        this.license = license;
        this.begin = begin;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String license() {
        return this.license;
    }

    @Override
    public long begin() {
        return this.begin;
    }
}
