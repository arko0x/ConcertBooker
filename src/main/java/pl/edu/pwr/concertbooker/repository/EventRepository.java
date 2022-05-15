package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Venue;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long> {
    Collection<Event> findByVenue(Venue venue);
}
