package br.com.clinica_vet.Service;

import br.com.clinica_vet.Domain.Dto.OwnerDto;
import br.com.clinica_vet.Domain.Owner;
import br.com.clinica_vet.Repositories.OwnerRepository;
import br.com.clinica_vet.Service.Exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Owner findById(String id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public Owner update(String id, OwnerDto ownerDto) {
        Owner owner = helperUpdate(id, ownerDto);
        return ownerRepository.save(owner);
    }

    public void delete(String id) {
        if (id.isEmpty()) {
            throw new ObjectNotFoundException("not found");
        }
        ownerRepository.deleteById(id);
    }

    private Owner helperUpdate(String id, OwnerDto ownerDto){
        Owner owner = findById(id);

        owner.setName(ownerDto.name());
        owner.setAddress(ownerDto.address());
        owner.setCity(ownerDto.city());
        owner.setTelephone(ownerDto.telephone());
        return owner;
    }
}