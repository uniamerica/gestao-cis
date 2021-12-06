package com.cis.repository;

import com.cis.model.HealthProfessional;
import com.cis.model.Room;
import com.cis.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByProfessional(HealthProfessional healthProfessional);
    List<Schedule> findByRoom(Room room);
}
