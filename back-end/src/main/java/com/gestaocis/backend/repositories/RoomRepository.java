package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

  Optional<Room> findByRoomNumber(Integer number);
}
