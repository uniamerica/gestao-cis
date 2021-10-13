package com.gestaocis.backend.enums;

public enum Role {
  ROLE_MANAGER("GESTOR"),
  ROLE_SECRETARY("SECRETARIA"),
  ROLE_PROFESSIONAL("PROFISSIONAL"),
  ROLE_SUPERVISOR("SUPERVISOR"),
  ROLE_INTERN("ESTAGIARIO"),
  ROLE_PATIENT("PACIENTE"),
  ROLE_ROOM("SALAS");

  private final String roleValue;

  Role(String roleValue) {
    this.roleValue = roleValue;
  }

  public String getRoleValue() {
    return roleValue;
  }
}