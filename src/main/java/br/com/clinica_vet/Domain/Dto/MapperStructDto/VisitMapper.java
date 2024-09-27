package br.com.clinica_vet.Domain.Dto.MapperStructDto;

import br.com.clinica_vet.Domain.Dto.VisitDto;
import br.com.clinica_vet.Domain.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PetMapper.class)
public interface VisitMapper {
    Visit visitToVisit(VisitDto visitDto);
    VisitDto visitToVisitDto(Visit visit);
}
