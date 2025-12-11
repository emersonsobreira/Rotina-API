package example.com.Rotina.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.com.Rotina.model.UsuarioModel;

@Repository
public interface UsuarioModelRepository extends JpaRepository<UsuarioModel, UUID> {

    Optional<UsuarioModel> findByName(String name); // Método atualizado

    Optional<UsuarioModel> findByEmail(String email);
}
