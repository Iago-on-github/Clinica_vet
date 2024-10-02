package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(String name);
}
