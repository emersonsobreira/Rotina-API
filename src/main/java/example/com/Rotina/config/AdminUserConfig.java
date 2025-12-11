package example.com.Rotina.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import example.com.Rotina.Role;
import example.com.Rotina.model.UsuarioModel;
import example.com.Rotina.repository.RoleRepository;
import example.com.Rotina.repository.UsuarioModelRepository;
import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsuarioModelRepository usuarioModelRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminUserConfig(RoleRepository roleRepository,
            UsuarioModelRepository userModelRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioModelRepository = userModelRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Obtém a role de administrador ou cria se não existir
        Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName(Role.Values.ADMIN.name());
            roleRepository.save(roleAdmin);
        }

        // Torna a roleAdmin efetivamente final para uso na expressão lambda
        final Role finalRoleAdmin = roleAdmin;

        // Verifica se o usuário admin já existe
        var userAdmin = usuarioModelRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin já existe"),
                () -> {
                    var usuario = new UsuarioModel();
                    usuario.setNome("admin");
                    usuario.setSenha(bCryptPasswordEncoder.encode("123"));
                    usuario.setRoles(Set.of(finalRoleAdmin)); // Usa a variável final
                    usuarioModelRepository.save(usuario);
                }
        );
    }

}
