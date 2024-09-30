package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.VisitDto;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Domain.Visit;
import br.com.clinica_vet.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitResources {
    @Autowired
    private VisitService visitService;

    @GetMapping
    public ResponseEntity<List<Visit>> findAll() {
        return ResponseEntity.ok().body(visitService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> findById(@PathVariable String id) {
        Visit visit = visitService.findById(id);
        Pet pet = visit.getPet();
        String petId = pet.getId();
        VisitDto dto = new VisitDto(visit.getId(), visit.getVisitDate(), visit.getDescription(), petId);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitDto> update(@PathVariable String id, @RequestBody VisitDto visitDto) {
        Visit visit = visitService.update(id, visitDto);
        VisitDto dto = new VisitDto(visit.getId(), visit.getVisitDate(), visit.getDescription(),  visit.getPet().getId());
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
