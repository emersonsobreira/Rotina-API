package example.com.Rotina.repository;

import example.com.Rotina.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
    Optional<UsuarioModel> findByEmail(String email);



}
