package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.Vet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VetRepository extends MongoRepository<Vet, String> {
}
