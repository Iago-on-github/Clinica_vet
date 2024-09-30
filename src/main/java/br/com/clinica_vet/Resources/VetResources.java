package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.VetDto;
import br.com.clinica_vet.Domain.Vet;
import br.com.clinica_vet.Service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetResources {
    @Autowired
    private VetService vetService;

    @GetMapping
    public ResponseEntity<List<VetDto>> findAll() {
        List<Vet> vet = vetService.findAll();
        List<VetDto> vetDto = vet.stream().map(x -> new VetDto(x.getId(), x.getName(), x.getSpecialty())).toList();
        return ResponseEntity.ok().body(vetDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetDto> findById(@PathVariable String id) {
        Vet vet = vetService.findById(id);
        VetDto dto = new VetDto(vet.getId(), vet.getName(), vet.getSpecialty());
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<VetDto> create(@RequestBody VetDto vetDto, UriComponentsBuilder componentsBuilder) {
        Vet vet = vetService.create(vetDto);
        VetDto dto = new VetDto(vet.getId(), vet.getName(), vet.getSpecialty());
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetDto> update(@PathVariable String id, @RequestBody VetDto vetDto) {
        Vet vet = vetService.update(id, vetDto);
        VetDto dto = new VetDto(vet.getId(), vet.getName(), vet.getSpecialty());
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        vetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
