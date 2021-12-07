package com.cis.controller;

import com.cis.model.Appointment;
import com.cis.model.dto.AppointmentDTO.AppointmentRequestDTO;
import com.cis.model.dto.AppointmentDTO.AppointmentResponseDTO;
import com.cis.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/appointments")
public class AppointmentController {

  private final AppointmentService service;

  public AppointmentController(AppointmentService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Page<Appointment>> list(Pageable pageable) {
    return ResponseEntity.ok(service.listAll(pageable));
  }

  @GetMapping(path = "/{uuid}")
  public ResponseEntity<Appointment> findByUUID(@PathVariable("uuid") UUID uuid) {
    return new ResponseEntity<>(service.findByUUID(uuid), HttpStatus.OK);
  }

  @GetMapping(path = "/find/hour/")
  public ResponseEntity<Appointment> findByHour(@RequestParam("hour") Integer hour) {
    return ResponseEntity.ok(service.findByHour(hour));
  }

  @GetMapping(path = "/find/minute/")
  public ResponseEntity<Appointment> findByMinute(@RequestParam("minute") Integer minute) {
    return ResponseEntity.ok(service.findByMinute(minute));
  }

  @GetMapping(path = "/find/professional/")
  public ResponseEntity<Appointment> findByProfessionalId(
      @RequestParam("professionalId") UUID professionalId) {
    return ResponseEntity.ok(service.findByProfessionalId(professionalId));
  }

  @GetMapping(path = "/find/room/")
  public ResponseEntity<Appointment> findByRoomId(@RequestParam("roomId") UUID roomId) {
    return ResponseEntity.ok(service.findByRoomId(roomId));
  }

  @GetMapping(path = "/find/patient/")
  public ResponseEntity<Appointment> findByPatientId(@RequestParam("patientId") UUID patientId) {
    return ResponseEntity.ok(service.findByPatientId(patientId));
  }

  @PostMapping
  public ResponseEntity<AppointmentResponseDTO> create(
      @RequestBody AppointmentRequestDTO appointment) {
    return new ResponseEntity<>(service.create(appointment), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Appointment> replace(@RequestBody @Valid Appointment appointment)
      throws Exception {
    service.update(appointment);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }
}
