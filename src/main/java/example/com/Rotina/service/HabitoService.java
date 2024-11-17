package example.com.Rotina.service;

import example.com.Rotina.models.HabitoModel;
import example.com.Rotina.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    public HabitoModel salvarHabito(HabitoModel habito) {
        return habitoRepository.save(habito);
    }

    // Outros métodos...
}
