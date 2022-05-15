package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Ticket;

import java.util.Collection;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Collection<Ticket> findTicketsByEventId(long eventId);
    Collection<Ticket> findTicketsByUserId(long userId);
}
