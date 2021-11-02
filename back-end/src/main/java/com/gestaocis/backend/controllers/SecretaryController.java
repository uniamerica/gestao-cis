package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.services.SecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/admin/secretaries")
public final class SecretaryController {

  @Autowired private SecretaryService secretaryService;

  @PostMapping
  public final ResponseEntity<SecretaryResponseDTO> save(
      @RequestBody NewSecretaryRequestDTO responseBody) throws Exception {
    try {
      return new ResponseEntity<>(this.secretaryService.save(responseBody), HttpStatus.CREATED);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GetMapping(path = "/id/{uuid}")
  public final ResponseEntity<SecretaryResponseDTO> findSecretaryByUUID(@PathVariable UUID uuid) {
    try {
      return new ResponseEntity<>(this.secretaryService.findByUUID(uuid), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GetMapping(path = "/email/{email}")
  public final ResponseEntity<SecretaryResponseDTO> findSecretaryByEmail(
      @PathVariable String email) {
    try {
      return new ResponseEntity<>(this.secretaryService.findByEmail(email), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GetMapping(path = "/name/{name}")
  public final ResponseEntity<List<SecretaryResponseDTO>> findListOfSecretariesByFullName(
      @PathVariable String name) {
    try {
      return new ResponseEntity<>(this.secretaryService.findByFullName(name), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GetMapping(path = "/role")
  public final ResponseEntity<List<SecretaryResponseDTO>> findAllByRole() {
    try {
      return new ResponseEntity<>(this.secretaryService.findByRole(), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @GetMapping(path = "/cpf/{cpf}")
  public final ResponseEntity<SecretaryResponseDTO> findSecretaryByCpf(@PathVariable String cpf) {
    try {
      return new ResponseEntity<>(this.secretaryService.findByCpf(cpf), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @PutMapping(path = "/{uuid}")
  public final ResponseEntity<SecretaryResponseDTO> updateSecretary(
      @PathVariable UUID uuid, @RequestBody NewSecretaryRequestDTO responseBody) {
    try {
      return new ResponseEntity<>(this.secretaryService.update(uuid, responseBody), HttpStatus.OK);
    } catch (Exception exception) {
      throw new BadRequestException(exception.getMessage());
    }
  }

  @DeleteMapping(path = "/{uuid}")
  public final ResponseEntity<String> deleteSecretary(@PathVariable UUID uuid) {
    if (this.secretaryService.delete(uuid)) {
      return new ResponseEntity<>("Delete With Success", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Fail on Delete", HttpStatus.BAD_REQUEST);
    }
  }
}
