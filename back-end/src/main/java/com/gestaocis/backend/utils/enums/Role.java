package com.gestaocis.backend.utils.enums;

public enum Role {
    ROLE_MANAGER("GESTOR"),
    ROLE_ADMIN("ADMIN"),
    ROLE_PATIENT("PACIENTE");

    private final String roleValue;

    Role(String value){
        roleValue = value;
    }

    public String getRoleValue(){
        return roleValue;
    }

}
