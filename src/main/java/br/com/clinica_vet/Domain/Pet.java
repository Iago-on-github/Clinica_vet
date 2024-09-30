package br.com.clinica_vet.Domain;

import br.com.clinica_vet.Domain.Enum.Race;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class Pet {
    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private int race;
    @Reference
    @JsonIgnore
    private Owner owner;

    public Pet(){}

    public Pet(String id, String name, LocalDate birthDate, Race race,Owner owner) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.race = race.getCode();
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Race getRace() {
        return Race.converted(race);
    }

    public void setRace(Race race) {
       if (race != null) {
           this.race = race.getCode();
       }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return Objects.equals(getId(), pet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
