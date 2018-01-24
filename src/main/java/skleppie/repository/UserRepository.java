package skleppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skleppie.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
