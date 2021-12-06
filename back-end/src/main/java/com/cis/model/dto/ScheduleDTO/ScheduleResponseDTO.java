package com.cis.model.dto.ScheduleDTO;

import com.cis.model.Schedule;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.model.dto.RoomDTO.RoomResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleResponseDTO {

    private UUID id;
    private HealthProfessionalResponseDTO professional;
    private RoomResponseDTO room;
    private Date date;
    private Integer hour;
    private Integer minutes;


    public ScheduleResponseDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.professional = new HealthProfessionalResponseDTO(schedule.getProfessional());
        this.room = new RoomResponseDTO(schedule.getRoom());
        this.date = schedule.getDate();
        this.hour = schedule.getHour();
        this.minutes = schedule.getMinutes();
    }
}
