package example.com.Rotina.controller;

import example.com.Rotina.models.HabitoModel;
import example.com.Rotina.models.UsuarioModel;
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

    @PostMapping("/usuarios/{usuarioId}/habitos")
    public ResponseEntity<Object> adicionarHabito(@PathVariable UUID usuarioId, @RequestBody @Valid HabitoModel habitoModel) {
        Optional<UsuarioModel> usuarioOptional = usuarioService.buscarPorId(usuarioId);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        UsuarioModel usuario = usuarioOptional.get();
        habitoModel.setUsuarioModel(usuario);
        habitoService.salvarHabito(habitoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hábito criado com sucesso.");
    }
}