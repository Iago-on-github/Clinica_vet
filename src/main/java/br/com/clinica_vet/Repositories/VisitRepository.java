package br.com.clinica_vet.Repositories;

import br.com.clinica_vet.Domain.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitRepository extends MongoRepository<Visit, String> {
}
