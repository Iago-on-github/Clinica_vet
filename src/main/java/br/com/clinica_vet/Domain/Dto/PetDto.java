package br.com.clinica_vet.Domain.Dto;

import br.com.clinica_vet.Domain.Owner;

import java.time.LocalDate;

public record PetDto(String name,
                     LocalDate birthDate) {
}
