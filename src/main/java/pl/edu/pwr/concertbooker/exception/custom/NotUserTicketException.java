package pl.edu.pwr.concertbooker.exception.custom;

public class NotUserTicketException extends Exception {
    public NotUserTicketException() {
        super("Ticket does not belong to a user");
    }
}
