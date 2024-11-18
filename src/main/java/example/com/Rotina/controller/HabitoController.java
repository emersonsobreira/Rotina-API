package example.com.Rotina.controller;


import java.time.LocalTime;
import example.com.Rotina.dto.HabitoDto;
import example.com.Rotina.model.HabitoModel;
import example.com.Rotina.model.UsuarioModel;
import example.com.Rotina.repository.HabitoRepository;
import example.com.Rotina.service.HabitoService;
import example.com.Rotina.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private HabitoRepository habitoRepository;

    @PostMapping("/usuarios/{usuarioId}/habitos")
    public ResponseEntity<Object> adicionarHabito(@PathVariable UUID usuarioId, @RequestBody @Valid HabitoDto habitoDto) {
        Optional<UsuarioModel> usuarioOptional = usuarioService.buscarPorId(usuarioId);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        // Mapeia HabitoDto para HabitoModel
        UsuarioModel usuario = usuarioOptional.get();
        HabitoModel habitoModel = new HabitoModel();
        habitoModel.setNome(habitoDto.getNome());
        habitoModel.setDescricao(habitoDto.getDescricao());
        habitoModel.setHorarioDesejado(LocalTime.parse(habitoDto.getHorarioDesejado())); // Converte String para LocalTime
        habitoModel.setFrequenciaSemanal(habitoDto.getFrequenciaSemanal());
        habitoModel.setUsuarioModel(usuario);

        habitoService.salvarHabito(habitoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hábito criado com sucesso.");
    }


    @PutMapping("/habitos/{id}")
    public ResponseEntity<Object> atualizarHabito(@PathVariable Long id, @RequestBody @Valid HabitoDto habitoDto) {
        Optional<HabitoModel> habitoExistente = habitoService.buscarPorId(id);

        if (habitoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito não encontrado.");
        }

        HabitoModel habito = habitoExistente.get();
        habito.setNome(habitoDto.getNome());
        habito.setDescricao(habitoDto.getDescricao());
        habito.setHorarioDesejado(LocalTime.parse(habitoDto.getHorarioDesejado()));
        habito.setFrequenciaSemanal(habitoDto.getFrequenciaSemanal());

        habitoService.salvarHabito(habito);

        return ResponseEntity.status(HttpStatus.OK).body("Hábito atualizado com sucesso.");
    }
    @GetMapping("/habitos/{id}")
    public ResponseEntity<Object> buscarHabito(@PathVariable Long id) {
        Optional<HabitoModel> habitoExistente = habitoService.buscarPorId(id);

        if (habitoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito não encontrado.");
        }

        HabitoModel habito = habitoExistente.get();
        HabitoDto habitoDto = new HabitoDto();
        habitoDto.setNome(habito.getNome());
        habitoDto.setDescricao(habito.getDescricao());
        habitoDto.setHorarioDesejado(habito.getHorarioDesejado().toString());
        habitoDto.setFrequenciaSemanal(habito.getFrequenciaSemanal());

        return ResponseEntity.status(HttpStatus.OK).body(habitoDto);
    }


    @DeleteMapping("/habitos/{id}")
    public ResponseEntity<Object> deletarHabito(@PathVariable Long id) {
        Optional<HabitoModel> habitoExistente = habitoService.buscarPorId(id);

        if (habitoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hábito não encontrado.");
        }

        habitoService.deletarHabito(id);
        return ResponseEntity.status(HttpStatus.OK).body("Hábito deletado com sucesso.");
    }


}
