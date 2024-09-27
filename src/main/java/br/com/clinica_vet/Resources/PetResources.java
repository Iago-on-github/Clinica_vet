package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.MapperStructDto.PetMapper;
import br.com.clinica_vet.Domain.Dto.PetDto;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetResources {
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> findAll(){
        return ResponseEntity.ok().body(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(petService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto> update(@PathVariable String id, @RequestBody PetDto petDto) {
        Pet pet = petService.update(id, petDto);
        PetDto dto = new PetDto(pet.getName(), pet.getBirthDate());
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
