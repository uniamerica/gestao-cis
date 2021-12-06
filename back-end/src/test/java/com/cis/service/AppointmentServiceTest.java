// package com.cis.service;
//
// import com.cis.model.Appointment;
// import com.cis.model.HealthProfessional;
// import com.cis.model.dto.AppointmentDTO.AppointmentResponseDTO;
// import com.cis.repository.AppointmentRepository;
// import com.cis.repository.HealthProfessionalRepository;
// import com.cis.repository.PatientRepository;
// import com.cis.repository.RoomRepository;
// import com.cis.utils.AppointmentCreator;
// import com.cis.utils.HealthProfessionalCreator;
// import com.cis.utils.PatientCreator;
// import com.cis.utils.RoomCreator;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentMatchers;
// import org.mockito.BDDMockito;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import java.text.ParseException;
// import java.util.Optional;
// import java.util.UUID;
//
// @ExtendWith(SpringExtension.class)
// public class AppointmentServiceTest {
//
//    @InjectMocks
//    private AppointmentService appointmentService;
//
//    @Mock
//    private AppointmentRepository appointmentRepository;
//
//    @Mock
//    private HealthProfessionalRepository healthProfessionalRepository;
//
//    @Mock
//    private PatientRepository patientRepository;
//
//    @Mock
//    private RoomRepository roomRepository;
//
//    @BeforeEach
//    void setup() throws ParseException {
//        BDDMockito.when(healthProfessionalRepository.findById(ArgumentMatchers.any()))
//
// .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));
//
//        BDDMockito.when(roomRepository.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(RoomCreator.createRoomToBeSaved()));
//
//        BDDMockito.when(patientRepository.findById(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(PatientCreator.createPatientSaved()));
//
//        BDDMockito.when(appointmentRepository.save(ArgumentMatchers.any()))
//                .thenReturn(Optional.of(AppointmentCreator.appointmentToSave()));
//    }
//
//    @Test
//    @DisplayName("Should return Appointment")
//    void test_create() throws ParseException {
//        var appointmentResponseDTO =
// appointmentService.create(AppointmentCreator.appointmentToSave());
//
//        Assertions.assertThat(appointmentResponseDTO).isNotNull();
//        Assertions.assertThat(appointmentResponseDTO.equals("Appointment Created with
// Success")).isTrue();
//    }
//
//    @Test
//    @DisplayName("Should update Appointment")
//    void test_update() throws ParseException {
//        String update = appointmentService.update(UUID.randomUUID(),
// AppointmentCreator.appointmentToSave());
//
//        BDDMockito.when(appointmentRepository.findById(ArgumentMatchers.any()))
//                        .thenReturn(Optional.of(AppointmentCreator.appointmentSaved()));
//
//        BDDMockito.when(healthProfessionalRepository.findById(ArgumentMatchers.any()))
//
// .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));
//
//        BDDMockito.when(roomRepository.findById(ArgumentMatchers.any()))
//                        .thenReturn((Optional.of(RoomCreator.createRoomToBeSaved())));
//
//        BDDMockito.when(patientRepository.findById(ArgumentMatchers.any()))
//                        .thenReturn(Optional.of(PatientCreator.createPatientSaved()));
//
//        Assertions.assertThat(update).isNotNull();
//        Assertions.assertThat(update.equals("Appointment modified with success")).isTrue();
//    }
// }
