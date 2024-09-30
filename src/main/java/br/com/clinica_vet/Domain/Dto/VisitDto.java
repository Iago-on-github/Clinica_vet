package br.com.clinica_vet.Domain.Dto;

import br.com.clinica_vet.Domain.Enum.Specialty;
import br.com.clinica_vet.Domain.Pet;

import java.time.LocalDate;

public record VisitDto(String id,
                       LocalDate visitDate,
                       String description,
                       String petId) {
}
