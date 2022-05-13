package pl.edu.pwr.concertbooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.model.Row;

public interface RowRepository extends JpaRepository<Row, Long> {
}
