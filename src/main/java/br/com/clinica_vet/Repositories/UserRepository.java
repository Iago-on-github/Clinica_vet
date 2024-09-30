package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
