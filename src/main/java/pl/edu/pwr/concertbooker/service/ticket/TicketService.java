package pl.edu.pwr.concertbooker.service.ticket;

import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.service.interfaces.ITicketService;

import java.util.Collection;
import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Override
    public Collection<Ticket> getTicketsForEventWithId(long id) {
        return null;
    }

    @Override
    public void cancelTickets(List<Long> ids) {

    }
}
