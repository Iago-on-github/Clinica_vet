package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.MapperStructDto.VisitMapper;
import br.com.clinica_vet.Domain.Dto.VisitDto;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Domain.Visit;
import br.com.clinica_vet.Service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/visits")
public class VisitResources {
    @Autowired
    private VisitMapper visitMapper;
    @Autowired
    private VisitService visitService;

    @GetMapping("/{id}")
    public ResponseEntity<Visit> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(visitService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VisitDto> create(@RequestBody VisitDto visitDto, UriComponentsBuilder componentsBuilder) {
        Visit visit = visitService.create(visitDto);
        VisitDto dto = visitMapper.visitToVisitDto(visit);
        URI uri = componentsBuilder.path("/{id}").buildAndExpand(visit.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
