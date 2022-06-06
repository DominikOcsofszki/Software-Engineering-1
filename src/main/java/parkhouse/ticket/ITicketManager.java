package parkhouse.ticket;

public interface ITicketManager {
    // not necessary at the moment


    void addTicket();
    void removeTicket(String id);
    void calculatePrice(String id, long duration);

}
