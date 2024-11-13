package example.com.Rotina.service;

import example.com.Rotina.models.UsuarioModel;
import example.com.Rotina.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioModel buscarPorId(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}