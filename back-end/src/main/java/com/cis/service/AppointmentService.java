package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.exceptions.ResourceNotFoundException;
import com.cis.model.Appointment;
import com.cis.model.HealthProfessional;
import com.cis.model.Patient;
import com.cis.model.Schedule;
import com.cis.model.dto.AppointmentDTO.AppointmentRequestDTO;
import com.cis.model.dto.AppointmentDTO.AppointmentResponseDTO;
import com.cis.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AppointmentService {
  private final AppointmentRepository appointmentRepository;
  private final RoomRepository roomRepository;
  private final PatientRepository patientRepository;
  private final HealthProfessionalRepository professionalRepository;
  private final ScheduleRepository scheduleRepository;

  public AppointmentService(
      AppointmentRepository appointmentRepository,
      RoomRepository roomRepository,
      PatientRepository patientRepository,
      HealthProfessionalRepository professionalRepository,
      ScheduleRepository scheduleRepository) {
    this.appointmentRepository = appointmentRepository;
    this.roomRepository = roomRepository;
    this.patientRepository = patientRepository;
    this.professionalRepository = professionalRepository;
    this.scheduleRepository = scheduleRepository;
  }

  public Page<Appointment> listAll(Pageable pageable) {
    return appointmentRepository.findAll(pageable);
  }

  public Appointment findByUUID(UUID uuid) {
    return appointmentRepository
        .findById(uuid)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByDate(Date date) {
    return appointmentRepository
        .findByDate(date)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByHour(Integer hour) {
    return appointmentRepository
        .findByHour(hour)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByMinute(Integer minute) {
    return appointmentRepository
        .findByMinute(minute)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByProfessionalId(UUID professionalId) {
    return appointmentRepository
        .findByProfessionalId(professionalId)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByRoomId(UUID roomId) {
    return appointmentRepository
        .findByRoomId(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByPatientId(UUID patientId) {
    return appointmentRepository
        .findByPatientId(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada."));
  }

  public Appointment findByBooking(
      Date date, Integer hour, Integer minute, Patient patient, HealthProfessional professional) {
    return appointmentRepository
        .findAppointmentByDateAndAndHourAndMinuteAndPatientAndProfessional(
            date, hour, minute, patient, professional)
        .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));
  }

  @Transactional
  public AppointmentResponseDTO create(AppointmentRequestDTO appointment) {

    Schedule schedule =
        scheduleRepository
            .findById(appointment.getScheduleId())
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado."));

    Patient patientById = patientRepository.findById(appointment.getPatientId()).get();

    HealthProfessional healthProfessionalById =
        professionalRepository.getById(schedule.getProfessional().getProfessional_id());

    Optional<Appointment> booking =
        appointmentRepository.findAppointmentByDateAndAndHourAndMinuteAndPatientAndProfessional(
            schedule.getDate(),
            schedule.getHour(),
            schedule.getMinutes(),
            patientById,
            healthProfessionalById);

    if (booking.isPresent()) {
      throw new BadRequestException("Agendamento já existente.");
    }

    Patient patient =
        patientRepository
            .findById(appointment.getPatientId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));

    Appointment appointmentToBeSaved =
        Appointment.builder()
            .date(schedule.getDate())
            .hour(schedule.getMinutes())
            .minute(schedule.getMinutes())
            .room(schedule.getRoom())
            .professional(schedule.getProfessional())
            .patient(patient)
            .observation(appointment.getObservation())
            .paid(appointment.getPaid())
            .fee(appointment.getFee())
            .build();

    Appointment save = appointmentRepository.save(appointmentToBeSaved);

    return new AppointmentResponseDTO(save);
  }

  public String update(UUID id, AppointmentRequestDTO appointment) {

    Schedule schedule =
        scheduleRepository
            .findById(appointment.getScheduleId())
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado."));

    Appointment appointment1 =
        appointmentRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado."));

    return "I need to be finished! :)";
  }

  public String delete(UUID id) {
    appointmentRepository.deleteById(id);
    return "Registro deletado com sucesso!";
  }

  public AppointmentResponseDTO update(Appointment appointment) {
    Optional<Appointment> appointmentToChange = appointmentRepository.findById(appointment.getId());

    if (appointmentToChange.isEmpty()) {
      throw new BadRequestException("Consulta não encontrada");
    } else {
      return new AppointmentResponseDTO(appointmentRepository.save(appointment));
    }
  }
}
