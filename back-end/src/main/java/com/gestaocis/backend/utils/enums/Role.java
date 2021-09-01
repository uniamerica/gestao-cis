package com.gestaocis.backend.utils.enums;

public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_SECRETARY("SECRETARIA(O)"),
    ROLE_INTERN("ESTAGIARIO(A)"),
    ROLE_PROFESSIONAL("PROFISSIONAL"),
    ROLE_PATIENT("PACIENTE");

    private final String roleValue;

    Role(String value){
        roleValue = value;
    }

    public String getRoleValue(){
        return roleValue;
    }

}
