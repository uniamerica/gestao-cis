package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.exceptions.ResourceNotFoundException;
import com.cis.model.Room;
import com.cis.model.Specialty;
import com.cis.model.dto.RoomDTO.RoomCreationDTO;
import com.cis.model.dto.RoomDTO.RoomUpdateDTO;
import com.cis.model.dto.SpecialtyDTO.SpecialtyCreationDTO;
import com.cis.repository.RoomRepository;
import com.cis.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

  private RoomRepository repository;
  private SpecialtyRepository specialtyRepository;

  public RoomService(RoomRepository repository, SpecialtyRepository specialtyRepository) {
    this.repository = repository;
    this.specialtyRepository = specialtyRepository;
  }

  public List<Room> listAll() {
    return repository.findAll();
  }

  public Room findByIdOrThrowError(UUID id) {
    Optional<Room> byId = repository.findById(id);

    if (byId.isEmpty()) {
      throw new ResourceNotFoundException("Sala não encontrada.");
    }
    return byId.get();
  }

  public Room findByNumberOrThrowError(String number) {
    Optional<Room> byId = repository.findByRoomNumber(number);

    if (byId.isEmpty()) {
      throw new ResourceNotFoundException("Sala não encontrada.");
    }
    return byId.get();
  }

  @Transactional
  public Room save(RoomCreationDTO room) {
    Optional<Room> findRoom = repository.findByRoomNumber(room.getRoomNumber());

    if (findRoom.isPresent()) {
      throw new BadRequestException("Sala já cadastrada em nosso sistema.");
    }

    List<SpecialtyCreationDTO> specialties = room.getSpecialties();
    List<Specialty> specialtiesToBeSaved = new ArrayList<>();

    specialties.forEach(
        specialty -> {
          Optional<Specialty> byNameIgnoreCase =
              specialtyRepository.findByNameIgnoreCase(specialty.getName());
          if (byNameIgnoreCase.isPresent()) {
            specialtiesToBeSaved.add(byNameIgnoreCase.get());
          } else {
            Specialty save =
                specialtyRepository.save(Specialty.builder().name(specialty.getName()).build());
            specialtiesToBeSaved.add(save);
          }
        });

    Room roomToBeSaved =
        Room.builder().roomNumber(room.getRoomNumber()).specialties(specialtiesToBeSaved).build();

    return repository.save(roomToBeSaved);
  }

  public void saveAll(List<Room> rooms) {
    repository.saveAll(rooms);
  }

  public String delete(UUID id) {
    repository.deleteById(id);
    return "Registro deletado com sucesso!";
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  public String update(UUID id, RoomUpdateDTO room) {

    Room savedRoom =
        repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada."));

    List<SpecialtyCreationDTO> specialties = room.getSpecialties();
    List<Specialty> specialtiesToBeSaved = new ArrayList<>();

    specialties.forEach(
        specialty -> {
          Optional<Specialty> byNameIgnoreCase =
              specialtyRepository.findByNameIgnoreCase(specialty.getName());
          if (byNameIgnoreCase.isPresent()) {
            specialtiesToBeSaved.add(byNameIgnoreCase.get());
          } else {
            Specialty save =
                specialtyRepository.save(Specialty.builder().name(specialty.getName()).build());
            specialtiesToBeSaved.add(save);
          }
        });

    savedRoom.setRoomNumber(room.getRoomNumber());
    savedRoom.setSpecialties(specialtiesToBeSaved);
    repository.save(savedRoom);
    return "Registro atualizado com sucesso!";
  }
}
