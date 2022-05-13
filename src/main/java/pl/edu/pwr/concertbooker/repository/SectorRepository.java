package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {
}
