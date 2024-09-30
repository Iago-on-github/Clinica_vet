package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.VetDto;
import br.com.clinica_vet.Domain.Vet;
import br.com.clinica_vet.Repositories.VetRepository;
import br.com.clinica_vet.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {
    @Autowired
    private VetRepository vetRepository;

    public List<Vet> findAll(){
        return vetRepository.findAll();
    }

    public Vet findById(String id) {
        return vetRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("NOt found"));
    }

    public Vet create(VetDto vetDto) {
        return vetRepository.save(helperCreate(vetDto));
    }

    public Vet update(String id, VetDto vetDto) {
        return vetRepository.save(helperUpdate(id, vetDto));
    }

    public void delete(String id) {
        if (id.isEmpty()) {
            throw new ObjectNotFoundException("Not found");
        }
        vetRepository.deleteById(id);
    }

    private Vet helperUpdate(String id, VetDto vetDto) {
        Vet vet = findById(id);
        vet.setName(vetDto.name());
        vet.setSpecialty(vetDto.specialty());
        return vet;
    }

    private Vet helperCreate(VetDto vetDto) {
        Vet vet = new Vet();
        vet.setId(vetDto.id());
        vet.setName(vetDto.name());
        vet.setSpecialty(vetDto.specialty());
        return vet;
    }
}
