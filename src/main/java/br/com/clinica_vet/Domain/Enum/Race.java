package br.com.clinica_vet.Domain.Enum;

public enum Race {
    DOG(1),
    CAT(2),
    FISH(3),
    HAMSTER(4),
    RATTLESNAKE(5);

    private int code;

    Race(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Race converted(int code) {
        for (Race s : Race.values()) {
            if (s.code == code) {
                return s;
            }
        }
        throw new RuntimeException("invalid code");
    }
}
