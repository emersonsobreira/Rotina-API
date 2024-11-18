package example.com.Rotina.repository;

import example.com.Rotina.model.HabitoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitoRepository extends JpaRepository<HabitoModel, Long> {
//    List<HabitoModel> findByUsuarioModel(UsuarioModel usuarioModel);
}
