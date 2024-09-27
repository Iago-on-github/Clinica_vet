package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {
}
