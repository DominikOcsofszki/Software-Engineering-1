package parkhouse.ticket;

public interface ITicketManager {

    void addTicket();
    void removeTicket(String id);
    void calculatePrice(String id, long duration);

}
