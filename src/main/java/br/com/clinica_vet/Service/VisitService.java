package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.VisitDto;
import br.com.clinica_vet.Domain.Visit;
import br.com.clinica_vet.Repositories.VisitRepository;
import br.com.clinica_vet.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Visit findById(String id) {
        return visitRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Not found"));
    }

    public Visit update(String id, VisitDto visitDto) {
        return visitRepository.save(helperUpdate(id, visitDto));
    }

    public void delete(String id) {
        if (id.isEmpty()) {
            throw new ObjectNotFoundException("Not found");
        }
        visitRepository.deleteById(id);
    }

    private Visit helperUpdate(String id, VisitDto visitDto) {
        Visit visit = findById(id);
        visit.setVisitDate(visitDto.visitDate());
        visit.setDescription(visitDto.description());
        return visit;
    }
}
