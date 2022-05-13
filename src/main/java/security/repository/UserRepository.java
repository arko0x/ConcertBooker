package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
