package br.com.clinica_vet.Domain.Dto;

import br.com.clinica_vet.Domain.Enum.Specialty;

public record VetDto(String id,
                     String name,
                     Specialty specialty) {
}
