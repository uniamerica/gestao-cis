package com.cis.utils;

import com.cis.model.Schedule;
import com.cis.model.dto.ScheduleDTO.ScheduleRequestDTO;
import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ScheduleCreator {

    public static Schedule createValidScheduleSaved(){
        return Schedule
                .builder()
                .id(UUID.randomUUID())
                .professional(HealthProfessionalCreator.createHealthProfessionalSaved())
                .room(RoomCreator.createRoomSaved())
                .date(Date.from(Instant.now()))
                .hour(11)
                .minutes(30)
                .build();
    }

    public static Schedule createValidScheduleToBeSaved(){
        return  Schedule.builder()
                .professional(HealthProfessionalCreator.createHealthProfessionalSaved())
                .room(RoomCreator.createRoomSaved())
                .date(Date.from(Instant.now()))
                .hour(11)
                .minutes(30)
                .build();
    }

    public static ScheduleRequestDTO createValidScheduleRequestDTOtoBeSaved(){
        return ScheduleRequestDTO
                .builder()
                .professional(UUID.randomUUID())
                .room(UUID.randomUUID())
                .date(Date.from(Instant.now()))
                .hour(11)
                .minutes(30)
                .build();
    }

    public static ScheduleResponseDTO createValidScheduleResponseDTOSaved(){
        return ScheduleResponseDTO
                .builder()
                .id(UUID.randomUUID())
                .date(Date.from(Instant.now()))
                .professional(HealthProfessionalCreator.createHealthProfessionalDTOSaved())
                .room(RoomCreator.createValidRoomResponseDTOSaved())
                .build();
    }
}
