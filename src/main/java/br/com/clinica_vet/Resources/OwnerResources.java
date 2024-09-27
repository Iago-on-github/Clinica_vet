package br.com.clinica_vet.Resources;

import br.com.clinica_vet.Domain.Dto.OwnerDto;
import br.com.clinica_vet.Domain.Owner;
import br.com.clinica_vet.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerResources {
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<Owner>> findAll() {
        List<Owner> list = ownerService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(ownerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@PathVariable String id, @RequestBody OwnerDto ownerDto) {
        Owner owner = ownerService.update(id, ownerDto);
        OwnerDto dto = new OwnerDto(owner.getName(), owner.getAddress(), owner.getCity(), owner.getTelephone());
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
