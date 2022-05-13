package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
