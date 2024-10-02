package br.com.clinica_vet.Domain.Dto;

import java.time.Instant;

public record UserResponse(String acessToken, Long expireIn) {
}
