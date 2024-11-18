package example.com.Rotina.service;

import example.com.Rotina.model.HabitoModel;
import example.com.Rotina.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    public HabitoModel salvarHabito(HabitoModel habito) {
        return habitoRepository.save(habito);
    }

    public Optional<HabitoModel> buscarPorId(Long id) {
        return habitoRepository.findById(id);
    }

    public void deletarHabito(Long id) {
        habitoRepository.deleteById(id);
    }

}




