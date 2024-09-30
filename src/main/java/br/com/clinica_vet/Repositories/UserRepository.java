package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByName(String username);
}
