package example.com.Rotina.repository;

import example.com.Rotina.models.HabitoModel;
import example.com.Rotina.models.RegistroAtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividadeModel, Long> {
    List<RegistroAtividadeModel> findByHabito(HabitoModel habitoModel);
}
