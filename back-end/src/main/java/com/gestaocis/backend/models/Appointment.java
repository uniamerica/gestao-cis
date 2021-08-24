package com.gestaocis.backend.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    UUID id;
    User patient;
    User professional;
    LocalDateTime createdAt;
    LocalDateTime editedAt;
    LocalDateTime scheduledFor;
    Room room;
    String observation;
    User createdBy;
    boolean isSupervisioned;
    boolean isConfirmed;
    boolean isPaid;
}
