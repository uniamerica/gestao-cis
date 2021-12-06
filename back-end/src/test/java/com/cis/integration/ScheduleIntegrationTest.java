package com.cis.integration;

import com.cis.model.HealthProfessional;
import com.cis.model.Room;
import com.cis.model.Schedule;
import com.cis.model.dto.ScheduleDTO.ScheduleRequestDTO;
import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.repository.RoomRepository;
import com.cis.repository.ScheduleRepository;
import com.cis.utils.HealthProfessionalCreator;
import com.cis.utils.PageableResponse;
import com.cis.utils.RoomCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ScheduleIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private HealthProfessionalRepository healthProfessionalRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @DisplayName("Schedule Integration Test - Index")
    public void test_index(){

        ResponseEntity<PageableResponse<ScheduleResponseDTO>> exchange = testRestTemplate.exchange(
                "/api/schedules",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageableResponse<ScheduleResponseDTO>>() {
                });

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Schedule Integration Test - FindById")
    public void test_findById(){
        HealthProfessional healthProfessional = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());
        Room room = roomRepository.save(RoomCreator.createRoomToBeSaved());

        Schedule build = Schedule.builder()
                .professional(healthProfessional)
                .room(room)
                .date(Date.from(Instant.now()))
                .hour(10)
                .minutes(30)
                .build();

        Schedule save = scheduleRepository.save(build);

        ResponseEntity<ScheduleResponseDTO> response = testRestTemplate.exchange("/api/schedules/{id}", HttpMethod.GET, null, ScheduleResponseDTO.class, save.getId());

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Schedule Integration Test - create")
    public void test_create(){
        HealthProfessional healthProfessional = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());
        Room room = roomRepository.save(RoomCreator.createRoomToBeSaved());

        ScheduleRequestDTO build = ScheduleRequestDTO
                .builder()
                .minutes(30)
                .hour(10)
                .date(Date.from(Instant.now()))
                .room(room.getId())
                .professional(healthProfessional.getId())
                .build();

        ResponseEntity<ScheduleResponseDTO> exchange = testRestTemplate.exchange("/api/schedules", HttpMethod.POST, new HttpEntity<>(build), ScheduleResponseDTO.class);

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Schedule Integration Test - update")
    public void test_update(){
        HealthProfessional healthProfessional = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());
        Room room = roomRepository.save(RoomCreator.createRoomToBeSaved());

        Schedule build = Schedule
                .builder()
                .professional(healthProfessional)
                .room(room)
                .date(Date.from(Instant.now()))
                .hour(10)
                .minutes(30)
                .build();

        Schedule save = scheduleRepository.save(build);

        ScheduleRequestDTO build2 = ScheduleRequestDTO
                .builder()
                .minutes(30)
                .hour(10)
                .date(Date.from(Instant.now()))
                .room(room.getId())
                .professional(healthProfessional.getId())
                .build();


        ResponseEntity<ScheduleResponseDTO> exchange = testRestTemplate.exchange("/api/schedules/{id}", HttpMethod.PUT, new HttpEntity<>(build2), ScheduleResponseDTO.class, save.getId());

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Schedule Integration Test - delete")
    public void test_delete(){
        HealthProfessional healthProfessional = healthProfessionalRepository.save(HealthProfessionalCreator.createHealthProfessionalToBeSaved());
        Room room = roomRepository.save(RoomCreator.createRoomToBeSaved());

        Schedule build = Schedule
                .builder()
                .professional(healthProfessional)
                .room(room)
                .date(Date.from(Instant.now()))
                .hour(10)
                .minutes(30)
                .build();

        Schedule save = scheduleRepository.save(build);


        ResponseEntity<String> exchange = testRestTemplate.exchange("/api/schedules/{id}", HttpMethod.DELETE, null, String.class, save.getId());

        Assertions.assertThat(exchange).isNotNull();
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);


    }

}
