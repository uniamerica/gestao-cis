package com.gestaocis.backend.utils.enums;

public enum Role {
  ROLE_MANAGER("GESTOR"),
  ROLE_PATIENT("PACIENTE"),
  ROLE_SECRETARY("SECRETARIA"),
  ROLE_PROFESSIONAL("PROFISSIONAL"),
  ROLE_SUPERVISOR("SUPERVISOR"),
  ROLE_INTERN("ESTAGIARIO");

  private final String roleValue;

  Role(String roleValue) {
    this.roleValue = roleValue;
  }

  public String getRoleValue() {
    return roleValue;
  }
}
