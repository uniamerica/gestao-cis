package com.gestaocis.backend.utils.enums;

public enum Specialty {
  SPECIALTY_PSYCHOLOGY("PSICOLOGIA"),
  SPECIALTY_NUTRITION("NUTRICAO"),
  SPECIALTY_PHYSIOTHERAPY("FISIOTERAPIA"),
  SPECIALTY_PSYCHIATRY("PSIQUIATRIA"),
  SPECIALTY_ACUPUNCTURE("ACUPUNCTURE "),
  SPECIALTY_THERAPEUTIC_MASSAGE("MASSAGEM_TERAPEUTICA"),
  SPECIALTY_PSYCHOPEDAGOGY("PSICOPEDAGOGIA"),
  SPECIALTY_SPEECH_THERAPY("FONOAUDIOLOGIA"),
  SPECIALTY_FACIAL_BODY_AESTHETICS("ESTETICA_FACIAL_CORPORAL");

  private final String specialtyValue;

  Specialty(String specialtyValue) {
    this.specialtyValue = specialtyValue;
  }

  public String getSpecialtyValue() {
    return specialtyValue;
  }
}
