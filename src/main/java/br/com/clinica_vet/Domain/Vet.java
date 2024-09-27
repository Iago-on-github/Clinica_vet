package br.com.clinica_vet.Domain;

import br.com.clinica_vet.Domain.Enum.Specialty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Vet {
    @Id
    private String id;
    private String name;
    private int specialty;

    public Vet(){}

    public Vet(String id, String name, Specialty specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty.getCode();
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

    public Specialty getSpecialty() {
        return Specialty.returnSpecialty(specialty);
    }

    public void setSpecialty(Specialty specialty) {
        if (specialty != null) {
            this.specialty = specialty.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vet vet)) return false;
        return Objects.equals(getId(), vet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
