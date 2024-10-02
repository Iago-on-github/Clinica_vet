package br.com.clinica_vet.SecurityConfigurations;

import br.com.clinica_vet.Domain.Role;
import br.com.clinica_vet.Domain.User;
import br.com.clinica_vet.Repositories.RoleRepository;
import br.com.clinica_vet.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var role = roleRepository.findByName(Role.values.ADMIN.name());
        var userAdmin = userRepository.findByLogin("admin");

        userAdmin.ifPresentOrElse(
                admin -> System.out.println("Admin already exists"),
                () -> {
                    var user = new User();
                    user.setLogin("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRoles(Set.of(role));
                    userRepository.save(user);
                }
        );
    }
}
