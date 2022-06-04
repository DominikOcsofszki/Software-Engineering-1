package parkhouse.ticket;

public class Ticket implements ITicket {

    private final String id;
    private final long begin;

    public Ticket(String id, String license, long begin) {
        this.id = id;
        this.begin = begin;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public long begin() {
        return this.begin;
    }
}
