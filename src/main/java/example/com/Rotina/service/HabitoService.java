package example.com.Rotina.service;

import example.com.Rotina.models.Habito;
import example.com.Rotina.models.UsuarioModel;
import example.com.Rotina.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitoService {

   private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    public Habito salvarHabito(Habito habito) {
        return habitoRepository.save(habito);
    }

    public List<Habito> listarHabitosPorUsuario(UsuarioModel usuario) {
        return habitoRepository.findByUsuarioModel(usuario);
    }

    public void deletarHabito(UUID id) {
        habitoRepository.deleteById(id);
    }
}

