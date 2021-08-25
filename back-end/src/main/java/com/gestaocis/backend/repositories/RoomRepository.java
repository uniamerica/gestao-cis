package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
