package example.com.Rotina.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import example.com.Rotina.model.UsuarioModel;
import example.com.Rotina.repository.UsuarioModelRepository;

@Service
public class UserDetailsService {

    private final UsuarioModelRepository usuarioModelRepository;

    public UserDetailsService(UsuarioModelRepository userModelRepository) {
        this.usuarioModelRepository = userModelRepository;
    }

    public Optional<UsuarioModel> buscarPorId(UUID id) {
        return usuarioModelRepository.findById(id);
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario) {
        return usuarioModelRepository.save(usuario);
    }

//    public Optional<UserModel> buscarPorEmail(String email) {
//        // Chame o método na instância do repositório
//        return userModelRepository.findByEmail(email);
//    }
}
