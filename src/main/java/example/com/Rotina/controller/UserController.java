package example.com.Rotina.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import example.com.Rotina.Role;
import example.com.Rotina.dto.CreateUserDto;
import example.com.Rotina.model.UserModel;
import example.com.Rotina.repository.RoleRepository;
import example.com.Rotina.repository.UserModelRepository;
import example.com.Rotina.service.UserDetailsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserModelRepository usuarioRepository;

    private final RoleRepository roleRepository;
    @Autowired
    private UserModelRepository userModelRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {

        // Verifica se a role básica existe
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        if (basicRole == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Role 'BASIC' não encontrada no sistema.");
        }

        // Verifica se o nome de usuário já está em uso
        var userFromDb = userModelRepository.findByName(dto.name());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Nome de usuário já está em uso.");
        }

        // Verifica se o e-mail já está em uso
        var emailFromDb = userModelRepository.findByEmail(dto.email());
        if (emailFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "E-mail já está em uso.");
        }

        // Criação do novo usuário
        var user = new UserModel();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        // Salva o usuário no banco de dados
        userModelRepository.save(user);

        // Retorna um status 201 indicando que o recurso foi criado com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsuarios() {
        List<UserModel> usuariosList = usuarioRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usuariosList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioO.get());
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid CreateUserDto userDto) {
        Optional<UserModel> usuarioO = usuarioRepository.findById(id);
        if (usuarioO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        var usuarioModel = usuarioO.get();
        // Copiar as propriedades do DTO para o modelo existente, mas não alterar o ID
        BeanUtils.copyProperties(userDto, usuarioModel, "id");
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable(value = "id") UUID id) {
        // Verifica se o usuário existe
        UserModel usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        usuarioRepository.delete(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}

