package example.com.Rotina.repository;

import example.com.Rotina.model.HabitoModel;
import example.com.Rotina.model.RegistroAtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividadeModel, Long> {
    List<RegistroAtividadeModel> findByHabito(HabitoModel habitoModel);
}
