package com.gestaocis.backend.services;

import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

  @Autowired private RoomRepository repository;

  public List<Room> listAll() {
    return repository.findAll();
  }

  public Optional<Room> findById(Long id) {
    return repository.findById(id);
  }

  public Optional<Room> findByRoomNumber(Integer roomNumber) {
    return repository.findByRoomNumber(roomNumber);
  }

  public Room save(Room room) {
    Optional<Room> salaProcurada = repository.findByRoomNumber(room.getRoomNumber());

    if (salaProcurada.isPresent()) {
      throw new BadRequestException("A sala já existe.");
    } else {
      return repository.save(room);
    }
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public void update(Room room) {
    Optional<Room> salaParaMudar = repository.findByRoomNumber(room.getRoomNumber());

    if (salaParaMudar.isEmpty()) {
      throw new BadRequestException("Sala não encontrada.");
    } else {
      repository.save(room);
    }
  }
}
