package example.com.Rotina.config;

import example.com.Rotina.Role;
import example.com.Rotina.model.UserModel;
import example.com.Rotina.repository.RoleRepository;
import example.com.Rotina.repository.UserModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserModelRepository userModelRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminUserConfig(RoleRepository roleRepository,
                           UserModelRepository userModelRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userModelRepository = userModelRepository;
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
        var userAdmin = userModelRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin já existe"),
                () -> {
                    var user = new UserModel();
                    user.setName("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRoles(Set.of(finalRoleAdmin)); // Usa a variável final
                    userModelRepository.save(user);
                }
        );
    }

}
