package skleppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skleppie.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
