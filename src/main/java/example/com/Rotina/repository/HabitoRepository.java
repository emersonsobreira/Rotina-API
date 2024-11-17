package example.com.Rotina.repository;

import example.com.Rotina.models.HabitoModel;
import example.com.Rotina.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HabitoRepository extends JpaRepository<HabitoModel, UUID> {
    List<HabitoModel> findByUsuarioModel(UsuarioModel usuarioModel);
}
