package example.com.Rotina.repository;

import example.com.Rotina.models.Habito;
import example.com.Rotina.models.RegistroAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, Long> {
    List<RegistroAtividade> findByHabito(Habito habito);
}
