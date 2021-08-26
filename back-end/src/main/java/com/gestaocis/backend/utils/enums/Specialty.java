package com.gestaocis.backend.utils.enums;
public enum Specialty {

    SPECIALTY_GENERAL_PRACTITIONER("Geral"),
    SPECIALTY_PSYCHOLOGY("Psicologia"),
    SPECIALTY_PHYSIOTHERAPY("Fisioterapia"),
    SPECIALTY_NUTRITION("Nutricao"),
    SPECIALTY_PSYCHIATRY("Psiquiatria"),
    SPECIALTY_ACUPUNTURE("Acupuntura"),
    SPECIALTY_MASSAGE("Massagem Terapeutica"),
    SPECIALTY_PSYCHOPEDAGOGY("Psicopedagogia"),
    SPECIALTY_SPEECH_THERAPY("Fonoaudiologia"),
    SPECIALTY_FACE_BODY_AESTHETICS("Estetica facial e corporal");

    private final String specialtyValue;

    Specialty(String value){
        specialtyValue = value;
    }

    public String getSpecialtyValue(){
        return specialtyValue;
    }
}