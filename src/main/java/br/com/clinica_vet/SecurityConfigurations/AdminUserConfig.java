package br.com.clinica_vet.SecurityConfigurations;

import br.com.clinica_vet.Domain.Role;
import br.com.clinica_vet.Domain.User;
import br.com.clinica_vet.Repositories.RoleRepository;
import br.com.clinica_vet.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
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
        initializeRoles();
        InitializeRoleBasic();
        
        var role = roleRepository.findByName(Role.Values.ADMIN.name());
        if (role == null) {
            throw new IllegalStateException("Role not found");
        }

        var userAdmin = userRepository.findByLogin("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin already exists");
                },
                () -> {
                    var user = new User();
                    user.setLogin("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("123"));
                    user.setRoles(Set.of(role)); // Agora garantido que 'role' não é nulo
                    userRepository.save(user);
                    System.out.println("Admin user created successfully");
                }
        );

    }

    private void initializeRoles() {
        if (roleRepository.findByName(Role.Values.ADMIN.name()) == null) {
            Role roleAdmin = new Role(null, Role.Values.ADMIN.name());
            roleRepository.save(roleAdmin);
            System.out.println("Role ADMIN created successfully");
        }
    }

    private void InitializeRoleBasic(){
        if (roleRepository.findByName(Role.Values.BASIC.name()) == null) {
            Role roleBasic = new Role(null, Role.Values.BASIC.name());
            roleRepository.save(roleBasic);
            System.out.println("Role BASIC created successfully");
        }
    }
}
