package example.com.Rotina.controller;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.com.Rotina.dto.HabitoDto;
import example.com.Rotina.model.HabitoModel;
import example.com.Rotina.model.UsuarioModel;
import example.com.Rotina.service.HabitoService;
import example.com.Rotina.service.UserDetailsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/usuarios/{usuarioId}/habitos")
    public ResponseEntity<Object> adicionarHabito(@PathVariable UUID usuarioId,
            @RequestBody @Valid HabitoDto habitoDto) {

        Optional<UsuarioModel> usuarioOptional = userDetailsService.buscarPorId(usuarioId);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        UsuarioModel usuario = usuarioOptional.get();
        HabitoModel habitoModel = new HabitoModel();

        habitoModel.setNome(habitoDto.getNome());
        habitoModel.setDescricao(habitoDto.getDescricao());
        habitoModel.setHorarioDesejado(LocalTime.parse(habitoDto.getHorarioDesejado()));
        habitoModel.setFrequenciaSemanal(habitoDto.getFrequenciaSemanal());
        habitoModel.setUsuarioModel(usuario);

        habitoService.salvarHabito(habitoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hábito criado com sucesso.");
    }

    @PutMapping("/habitos/{id}")
    public ResponseEntity<Object> atualizarHabito(@PathVariable Long id,
            @RequestBody @Valid HabitoDto habitoDto) {

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
