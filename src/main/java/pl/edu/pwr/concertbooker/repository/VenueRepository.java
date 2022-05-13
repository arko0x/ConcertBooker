package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
