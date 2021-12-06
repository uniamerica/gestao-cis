package com.cis.model.dto.ScheduleDTO;

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
public class ScheduleRequestDTO {
    private UUID professional;
    private UUID room;
    private Date date;
    private Integer hour;
    private Integer minutes;
}


