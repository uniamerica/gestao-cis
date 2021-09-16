package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Appointment;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.enums.SpecialtyEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class AppointmentRepositoryTest {

  @Autowired private AppointmentRepository appointmentRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private RoleEntityRepository roleEntityRepository;

  @Autowired private SpecialtyEntityRepository specialtyEntityRepository;

  @Autowired private RoomRepository roomRepository;

  private User createUserProfessional() {
    RoleEntity roleEntity =
        roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_PROFESSIONAL).build());

    SpecialtyEntity specialtyEntity =
        specialtyEntityRepository.save(
            SpecialtyEntity.builder().specialtyName("FISIOTERAPIA").build());

    return User.builder()
        .role(roleEntity)
        .specialty(specialtyEntity)
        .professionalDocument("xxxxxxxx")
        .phone("(45)99999999")
        .email("teste@test.com")
        .cpf("898989898989")
        .rg("88889888898")
        .fullName("Fulano de Tal")
        .password("senha1234")
        .build();
  }

  private User createUserPatient() {
    RoleEntity roleEntity =
        roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_PATIENT).build());

    return User.builder()
        .role(roleEntity)
        .phone("(45)990909090")
        .email("teste2@test.com")
        .cpf("999666996")
        .rg("asdada123")
        .fullName("sicrano")
        .password("senha")
        .build();
  }

  private User createUserCreator() {

    RoleEntity roleEntity =
        roleEntityRepository.save(RoleEntity.builder().roleName(Role.ROLE_SECRETARY).build());

    return User.builder()
        .role(roleEntity)
        .phone("(45)1234123123")
        .email("teste3@test.com")
        .cpf("9412314123131")
        .rg("a1231311")
        .fullName("beltrano")
        .password("senha")
        .build();
  }

  private Room createRoom() {
    return Room.builder().roomNumber(113).build();
  }

  private Appointment createAppointment() {

    User patient = userRepository.save(createUserPatient());
    User professional = userRepository.save(createUserProfessional());
    User creator = userRepository.save(createUserCreator());

    Room room = roomRepository.save(createRoom());

    return Appointment.builder()
        .patient(patient)
        .professional(professional)
        .createdAt(LocalDateTime.now())
        .editedAt(LocalDateTime.now())
        .scheduledFor(LocalDateTime.of(2021, 10, 30, 10, 30))
        .room(room)
        //                .observation("Doutor, cingaro da cancer?")
        .createdBy(creator)
        //                .isSupervised(false)
        //                .isConfirmed(false)
        //                .isPaid(false)
        .build();
  }

  @Test
  @DisplayName("Save Appointment When Successful")
  void save_appointment_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    Assertions.assertThat(appointmentSaved).isNotNull();
  }

  @Test
  @DisplayName("Find List Of Appointments By Professional When Successful")
  void find_listOfAppointmentsByProfessional_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    User professional = appointmentSaved.getProfessional();

    List<Appointment> appointmentList = appointmentRepository.findByProfessional(professional);

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Find List Of Appointments By CreatedBy When Successful")
  void find_listOfAppointmentsByCreatedBy_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    User creator = appointmentSaved.getCreatedBy();

    List<Appointment> appointmentList = appointmentRepository.findByCreatedBy(creator);

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Find List Of Appointments By Patient When Successful")
  void find_listOfAppointmentsByPatient_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    User patient = appointmentSaved.getPatient();

    List<Appointment> appointmentList = appointmentRepository.findByPatient(patient);

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Find List Of Appointments By ScheduledForBetween When Successful")
  void find_listOfAppointmentsByScheduledForBetween_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    LocalDateTime date1 = LocalDateTime.of(2021, 10, 29, 0, 0);
    LocalDateTime date2 = LocalDateTime.of(2021, 10, 30, 23, 59);

    List<Appointment> appointmentList =
        appointmentRepository.findByScheduledForBetween(date1, date2);

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Find List of Appointments by Room When Successful")
  void find_listOfAppointmentsByRoom_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    Room room = appointmentSaved.getRoom();

    List<Appointment> appointmentList = appointmentRepository.findByRoom(room);

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Find Appointment By Scheduled For when Successful")
  void find_AppointmentByScheduledFor_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    LocalDateTime date = appointment.getScheduledFor();

    Optional<Appointment> appointmentFound = appointmentRepository.findByScheduledFor(date);

    Assertions.assertThat(appointmentFound).isNotNull().isNotEmpty();

    Assertions.assertThat(appointmentFound.get()).isEqualTo(appointmentSaved);
  }

  @Test
  @DisplayName("find List Of Appointments when Successful")
  void find_listOfAppointments_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);

    List<Appointment> appointmentList = appointmentRepository.findAll();

    Assertions.assertThat(appointmentList).isNotNull().isNotEmpty().contains(appointmentSaved);
  }

  @Test
  @DisplayName("Update Appointment when Successful")
  void update_appointment_whenSuccessful() {
    Appointment appointment = createAppointment();

    Appointment appointmentSaved = appointmentRepository.save(appointment);
    appointmentSaved.setConfirmed(true);
    appointmentSaved.setPaid(true);
    appointmentSaved.setEditedAt(LocalDateTime.now());

    Appointment appointmentUpdated = appointmentRepository.save(appointmentSaved);

    Assertions.assertThat(appointmentUpdated.getId()).isEqualTo(appointmentSaved.getId());
    Assertions.assertThat(appointmentUpdated.isPaid()).isTrue();
    Assertions.assertThat(appointmentUpdated.isConfirmed()).isTrue();
  }
}
