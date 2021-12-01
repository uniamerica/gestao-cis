package com.gestaocis.backend.services;

import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.repositories.RoomRepository;
import com.gestaocis.backend.repositories.SpecialtyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public Room create(Room room) {
        Room quartinho = Room.builder().roomNumber(room.getRoomNumber()).uuid(UUID.randomUUID()).build();
        return repository.save(quartinho);
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

    public Room findById(Long id) {
        Optional<Room> found = repository.findById(id);

        if(found.isPresent()) return found.get();
        else return null;
    }

    public Room findByRoomNumber(int number) {
        Optional<Room> found = repository.findByRoomNumber(number);

        if (found.isPresent()) return found.get();
        else return null;
    }

    public Room update(Room room) {
        return repository.save(room);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
