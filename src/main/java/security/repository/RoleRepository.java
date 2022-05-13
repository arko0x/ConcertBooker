package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
