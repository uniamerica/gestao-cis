package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.Specialty;
import com.cis.model.dto.SpecialtyDTO.SpecialtyCreationDTO;
import com.cis.model.dto.SpecialtyDTO.SpecialtyReturnDTO;
import com.cis.model.dto.SpecialtyDTO.SpecialtyUpdateDTO;
import com.cis.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpecialtyService {

  private SpecialtyRepository repository;

  public SpecialtyService(SpecialtyRepository repository) {
    this.repository = repository;
  }

  public List<SpecialtyReturnDTO> listAll() {
    List<Specialty> repositoryAll = repository.findAll();

    List<SpecialtyReturnDTO> specialties = new ArrayList<>();

    for (Specialty specialty : repositoryAll) {
      specialties.add(new SpecialtyReturnDTO(specialty));
    }

    return specialties;
  }

  public SpecialtyReturnDTO findByIdOrThrowError(UUID id) {
    Optional<Specialty> specialty = repository.findById(id);

    if (specialty.isEmpty()) {
      throw new BadRequestException("Especialidade não encontrada.");
    } else {
      return new SpecialtyReturnDTO(specialty.get());
    }
  }

  public SpecialtyReturnDTO findByNameOrThrowError(String name) {
    Optional<Specialty> specialty = repository.findByNameIgnoreCase(name);

    if (specialty.isEmpty()) {
      throw new BadRequestException("Especialidade não encontrada.");
    } else {
      return new SpecialtyReturnDTO(specialty.get());
    }
  }

  @Transactional
  public SpecialtyReturnDTO save(SpecialtyCreationDTO specialty) {
    Optional<Specialty> findSpecialty = repository.findByNameIgnoreCase(specialty.getName());

    if (findSpecialty.isPresent()) {
      throw new BadRequestException("Especialidade já cadastrada em nosso sistema.");
    }

    Specialty specialtyToBeSaved =
        repository.save(Specialty.builder().name(specialty.getName()).build());

    return new SpecialtyReturnDTO(specialtyToBeSaved);
  }

  public void saveAll(List<Specialty> addresses) {
    repository.saveAll(addresses);
  }

  public String delete(UUID id) {
    repository.deleteById(id);
    return "Registro deletado com sucesso!";
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  public String update(UUID id, SpecialtyUpdateDTO specialty) {

    Specialty savedSpecialty = repository.findById(id).get();

    savedSpecialty.setName(specialty.getName());
    repository.save(savedSpecialty);
    return "Registro atualizado com sucesso!";
  }
}
