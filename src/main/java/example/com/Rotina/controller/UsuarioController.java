package example.com.Rotina.controller;

import example.com.Rotina.dto.UsuarioDto;
import example.com.Rotina.model.UsuarioModel;
import example.com.Rotina.repository.UsuarioRepository;
import example.com.Rotina.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        UsuarioModel usuarioCriado = usuarioService.salvarUsuario(usuarioModel);
        return ResponseEntity.ok(usuarioCriado);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
        List<UsuarioModel> usuariosList = usuarioRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuariosList);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
        }
    }


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto) {
        Optional<UsuarioModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var usuarioModel = usuarioO.get();
        // Copiar as propriedades do DTO para o modelo existente, mas não alterar o ID
        BeanUtils.copyProperties(usuarioDto, usuarioModel, "id");
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));
    }


    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable(value = "id") UUID id) {
        // Verifica se o usuário existe
        UsuarioModel usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        usuarioRepository.delete(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }
}

