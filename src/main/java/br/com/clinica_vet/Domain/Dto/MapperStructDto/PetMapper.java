package br.com.clinica_vet.Domain.Dto.MapperStructDto;

import br.com.clinica_vet.Domain.Dto.PetDto;
import br.com.clinica_vet.Domain.Pet;
import ch.qos.logback.core.model.ComponentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetDto petToPetDto(Pet pet);
    Pet petDtoToPet(PetDto petDto);
}
