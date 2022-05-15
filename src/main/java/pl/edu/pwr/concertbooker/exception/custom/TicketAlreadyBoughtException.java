package pl.edu.pwr.concertbooker.exception.custom;

public class TicketAlreadyBoughtException extends Exception {
    public TicketAlreadyBoughtException(long id) {
        super("Ticket with id " + id + "has already been bought");
    }
}
