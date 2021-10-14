package com.gestaocis.backend.repositories;

import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(Integer number);

    List<User> findByRole(RoleEntity role);
}