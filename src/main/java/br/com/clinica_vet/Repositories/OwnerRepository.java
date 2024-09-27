package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, String> {
}
