package com.gestaocis.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID scheduleId;
    private LocalDate startDate;
    private LocalDate endDate;
    //private UUID UserId;

    public void createSchedule(){

    }

    public void editSchedule(){

    }

    public void deleteSchedule(){

    }

    public void listSchedule(){

    }
}
