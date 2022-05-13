package pl.edu.pwr.concertbooker.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.concertbooker.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
