package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.model.Ticket;

import java.util.Collection;
import java.util.List;

public interface ITicketService {
    Collection<Ticket> getTicketsForEventWithId(long id);
    void cancelTickets(List<Long> ids);
}
