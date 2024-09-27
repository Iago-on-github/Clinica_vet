package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.MapperStructDto.PetMapper;
import br.com.clinica_vet.Domain.Dto.MapperStructDto.VisitMapper;
import br.com.clinica_vet.Domain.Dto.PetDto;
import br.com.clinica_vet.Domain.Dto.VisitDto;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Domain.Visit;
import br.com.clinica_vet.Repositories.PetRepository;
import br.com.clinica_vet.Repositories.VisitRepository;
import br.com.clinica_vet.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private VisitMapper visitMapper;

    public Visit findById(String id) {
        return visitRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Not found"));
    }

    public Visit create(VisitDto visitDto) {
        Pet pet1 = petService.convertToEntity(new PetDto(visitDto.petId(), visitDto.visitDate()));
        Pet pet = petRepository.findById(visitDto.petId())
                .orElseThrow(() -> new ObjectNotFoundException("Pet not found"));

        Visit visit = visitMapper.visitToVisit(visitDto);
        visit.setPet(pet);
        return visitRepository.save(visit);
    }
}
