package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.MapperStructDto.PetMapper;
import br.com.clinica_vet.Domain.Dto.PetDto;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Repositories.PetRepository;
import br.com.clinica_vet.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public Pet findById(String id) {
        Optional<Pet> pet = petRepository.findById(id);

        return pet.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public Pet update(String id, PetDto petDto) {
        return petRepository.save(helperUpdate(id, petDto));
    }

    public void delete(String id) {
        if (id.isEmpty()) {
            throw new ObjectNotFoundException("not found");
        }
        petRepository.deleteById(id);
    }

    private Pet helperUpdate(String id, PetDto petDto) {
        Pet pet = findById(id);
        pet.setName(petDto.name());
        pet.setBirthDate(petDto.birthDate());
        return pet;
    }

    //métodos para converter Dto para Entity e Entity para Dto
    public PetDto convertToDto(Pet pet) {
        return petMapper.INSTANCE.petToPetDto(pet);
    }

    public Pet convertToEntity(PetDto petDto){
        return petMapper.petDtoToPet(petDto);
    }
}
