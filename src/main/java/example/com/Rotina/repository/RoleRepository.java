package example.com.Rotina.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.Rotina.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//   Optional<Role> findByEmail(String email);
    Role findByName(String name);
}
