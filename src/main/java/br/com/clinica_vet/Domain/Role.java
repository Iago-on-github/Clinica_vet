package br.com.clinica_vet.Domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
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

    public enum values{
        ADMIN(1L),
        BASIC(2L);

        long code;

        values(long code) {
            this.code = code;
        }

        public long getCode() {
            return code;
        }
    }
}
