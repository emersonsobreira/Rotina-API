package example.com.Rotina.service;

import example.com.Rotina.model.UserModel;
import example.com.Rotina.repository.UserModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDetailsService {

    private final UserModelRepository userModelRepository;

    public UserDetailsService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    public Optional<UserModel> buscarPorId(UUID id) {
        return userModelRepository.findById(id);
    }

    public UserModel salvarUsuario(UserModel user) {
        return userModelRepository.save(user);
    }

//    public Optional<UserModel> buscarPorEmail(String email) {
//        // Chame o método na instância do repositório
//        return userModelRepository.findByEmail(email);
//    }
}