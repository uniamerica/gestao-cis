package com.cis.repository;

import com.cis.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

  Optional<Room> findByRoomNumber(String number);
}
