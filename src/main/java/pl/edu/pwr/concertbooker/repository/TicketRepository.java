package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
