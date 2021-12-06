package com.cis.controller;

import com.cis.model.dto.ScheduleDTO.ScheduleRequestDTO;
import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;
import com.cis.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDTO>> index(Pageable pageable){
        return new ResponseEntity<>(scheduleService.index(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable UUID id){
        return new ResponseEntity<>(scheduleService.findByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> create(@RequestBody ScheduleRequestDTO requestDTO){
        return new ResponseEntity<>(scheduleService.create(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ScheduleResponseDTO> update(@PathVariable UUID id, @RequestBody ScheduleRequestDTO requestDTO){
        return new ResponseEntity<>(scheduleService.update(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id){
        scheduleService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path ="/professional/{id}")
    public ResponseEntity<String> deleteAllByProfessionalId(@PathVariable UUID id){
        scheduleService.deleteAllByProfessional(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path ="/room/{id}")
    public ResponseEntity<String> deleteAllByRoomId(@PathVariable UUID id){
        scheduleService.deleteAllByRoom(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
}
