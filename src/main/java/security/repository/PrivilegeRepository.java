package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
