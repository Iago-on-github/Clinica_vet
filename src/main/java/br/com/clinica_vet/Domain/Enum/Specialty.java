package br.com.clinica_vet.Domain.Enum;

public enum Specialty {
    CIRURGIA(1),
    CARDIOLOGIA(2),
    DERMATOLOGIA(3),
    ANESTESIOLOGIA(4);

    private int code;

    Specialty(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Specialty returnSpecialty(int code) {
        for (Specialty s : Specialty.values()){
            if (s.code == code) {
                return s;
            }
        }
        throw new RuntimeException("Error in converted code.");
    }
}
