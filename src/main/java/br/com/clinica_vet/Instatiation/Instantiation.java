package br.com.clinica_vet.Instatiation;

import br.com.clinica_vet.Domain.Enum.Race;
import br.com.clinica_vet.Domain.Owner;
import br.com.clinica_vet.Domain.Pet;
import br.com.clinica_vet.Repositories.OwnerRepository;
import br.com.clinica_vet.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ownerRepository.deleteAll();
        petRepository.deleteAll();

        Owner o1 = new Owner(null, "Marcos", "Rua Marquino de Ferraz", "São Paulo", "89637193");
        Owner o2 = new Owner(null, "Gabriella", "Rua Teodoro", "Rio de Janeiro", "19757193");
        Owner o3 = new Owner(null, "Geovanni", "Rua Ferraz", "Salvador", "89630923");

        ownerRepository.saveAll(Arrays.asList(o1, o2, o3));

        Pet p1 = new Pet(null, "Mell", LocalDate.parse("2020-03-01", dt), Race.DOG, o3);
        Pet p2 = new Pet(null, "Eduardo", LocalDate.parse("2023-01-21", dt), Race.DOG, o1);
        Pet p3 = new Pet(null, "Cami", LocalDate.parse("2021-10-01", dt), Race.CAT, o3);

        petRepository.saveAll(Arrays.asList(p1, p2, p3));

        o1.getPets().add(p2);
        o3.getPets().addAll(Arrays.asList(p1, p3));

        ownerRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
