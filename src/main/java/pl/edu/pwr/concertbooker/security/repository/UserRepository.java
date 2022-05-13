package pl.edu.pwr.concertbooker.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
