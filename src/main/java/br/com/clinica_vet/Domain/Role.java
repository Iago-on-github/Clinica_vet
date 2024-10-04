package br.com.clinica_vet.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
    @Id
    private String id;
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
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

    public enum Values{
        ADMIN(1L),
        BASIC(2L);

        long code;

        Values(long code) {
            this.code = code;
        }

        public long getCode() {
            return code;
        }
    }
}
