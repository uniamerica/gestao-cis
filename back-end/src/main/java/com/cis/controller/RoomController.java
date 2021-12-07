package com.cis.controller;

import com.cis.model.Room;
import com.cis.model.dto.RoomDTO.RoomCreationDTO;
import com.cis.model.dto.RoomDTO.RoomUpdateDTO;
import com.cis.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

  private RoomService service;

  public RoomController(RoomService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Room>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Room> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowError(id));
  }

  @GetMapping(path = "/find")
  public ResponseEntity<Room> findByName(@RequestParam("number") String number) {
    return ResponseEntity.ok(service.findByNumberOrThrowError(number));
  }

  @PostMapping
  public ResponseEntity<Room> save(@RequestBody @Valid RoomCreationDTO room) {
    return new ResponseEntity<>(service.save(room), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody RoomUpdateDTO room)
      throws Exception {
    return new ResponseEntity<>(service.update(id, room), HttpStatus.OK);
  }
}
