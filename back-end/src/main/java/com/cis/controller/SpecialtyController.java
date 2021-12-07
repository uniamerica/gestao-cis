package com.cis.controller;

import com.cis.exceptions.ResourceNotFoundException;
import com.cis.model.dto.SpecialtyDTO.SpecialtyCreationDTO;
import com.cis.model.dto.SpecialtyDTO.SpecialtyReturnDTO;
import com.cis.model.dto.SpecialtyDTO.SpecialtyUpdateDTO;
import com.cis.service.SpecialtyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

  private SpecialtyService service;

  public SpecialtyController(SpecialtyService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<SpecialtyReturnDTO>> listAll() {
    return ResponseEntity.ok(service.listAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<SpecialtyReturnDTO> findById(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(service.findByIdOrThrowError(id));
  }

  @GetMapping(path = "/find")
  public ResponseEntity<SpecialtyReturnDTO> findByName(@RequestParam("name") String name) {
    return ResponseEntity.ok(service.findByNameOrThrowError(name));
  }

  @PostMapping
  public ResponseEntity<SpecialtyReturnDTO> save(
      @RequestBody @Valid SpecialtyCreationDTO specialty) {
    return new ResponseEntity<>(service.save(specialty), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
    return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<String> update(
      @PathVariable UUID id, @RequestBody SpecialtyUpdateDTO specialty) throws Exception {
    SpecialtyReturnDTO dto = service.findByIdOrThrowError(id);
    if (dto.getId() == null) {
      throw new ResourceNotFoundException(
          "Paciente não encontrado, por favor revise o ID enviado.");
    }
    return new ResponseEntity<>(service.update(id, specialty), HttpStatus.OK);
  }
}
