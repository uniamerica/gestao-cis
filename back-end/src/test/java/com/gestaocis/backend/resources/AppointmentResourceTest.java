package com.gestaocis.backend.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestaocis.backend.dtos.appointment.AppointmentDTOPost;
import com.gestaocis.backend.dtos.user.UserPatientDTO;
import com.gestaocis.backend.dtos.user.UserProfessionalDTO;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.models.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
@Log4j2
public class AppointmentResourceTest {

  static String CIS_API = "/api/v1/appointments";

  @Autowired MockMvc mvc; // mock de requisições

  @Test
  @DisplayName("Should create a new Appointment record")
  public void createAppointmentTest() throws Exception {

    UserPatientDTO patient = new UserPatientDTO();
    UserProfessionalDTO professional = new UserProfessionalDTO();
    User createdBy = new User();
    Room room = new Room();
    LocalDateTime currentDate = LocalDateTime.of(2021, 9, 6, 9, 30, 0, 0);

    AppointmentDTOPost dto =
        AppointmentDTOPost.builder()
            .patient(patient)
            .professional(professional)
            .scheduledFor(currentDate)
            .room(new Room())
            .observation("This is an observation")
            .supervised(false)
            .paid(true)
            .build();

    log.info(dto.getScheduledFor().toString());

    ObjectMapper objectMapper = new ObjectMapper();
    //    objectMapper.findAndRegisterModules();
    String json = objectMapper.writeValueAsString(dto);

    log.info(json);

    MockHttpServletRequestBuilder request =
        MockMvcRequestBuilders.post(CIS_API)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json);

    mvc.perform(request)
        .andExpect(status().isCreated())
        //        .andExpect(jsonPath("id").isNotEmpty())
        //        .andExpect(jsonPath("uuid").isNotEmpty())
        .andExpect(jsonPath("patient").value(dto.getPatient()))
        .andExpect(jsonPath("professional").value(dto.getProfessional()))
        .andExpect(jsonPath("scheduledFor").value(dto.getScheduledFor()))
        .andExpect(jsonPath("room").value(dto.getRoom()))
        .andExpect(jsonPath("observation").value(dto.getObservation()))
        .andExpect(jsonPath("supervisioned").value(dto.isSupervised()))
        .andExpect(jsonPath("paid").value(dto.isPaid()));
  }

  @Test
  @DisplayName("Should throw validation error when not all Appointment fields are valid")
  public void createInvalidAppointmentTest() {}
}
