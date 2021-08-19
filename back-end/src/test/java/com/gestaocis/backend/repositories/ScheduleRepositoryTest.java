package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Schedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@DisplayName("Testes for schedule repository")
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    private Schedule createFakeScheduleData(){
        Schedule schedule = new Schedule();
        UUID scheduleId = new UUID(1L, 1L);
        LocalDate startDate = LocalDate.now();

        return schedule;
    }

    @Test
    @DisplayName("Save Schedule")
    void save_persistSchedule(){
        Schedule fakeSchedule = createFakeScheduleData();
        Schedule savedSchedule = scheduleRepository.save(fakeSchedule);

        //  Verificar se o objeto não está vazio
        Assertions.assertThat(savedSchedule).isNotNull();
        //Assertions.assertEquals(fakeSchedule.getScheduleId(), savedSchedule.getScheduleId());
    }

    @Test
    @DisplayName("Find Shedule by ID")
    void list_findScheduleById(){
        Schedule fakeSchedule = createFakeScheduleData();
        Schedule savedSchedule = scheduleRepository.save(fakeSchedule);

        Optional<Schedule> secretaryInDatabase = scheduleRepository.findById(savedSchedule.getScheduleId());

        Assertions.assertThat(secretaryInDatabase.get().getScheduleId()).isEqualTo(savedSchedule.getScheduleId());
    }

    @Test
    @DisplayName("List Schedules")
    void list_AllSchedules(){
        Schedule fakeSchedule = createFakeScheduleData();
        Schedule savedSchedule = scheduleRepository.save(fakeSchedule);

        List<Schedule> ListOfSchedules = scheduleRepository.findAll();

        Assertions.assertThat(ListOfSchedules).isNotNull();
    }

    @Test
    @DisplayName("Update Schedules")
    void update_Schedules(){
        Schedule fakeSchedule = createFakeScheduleData();
        // É necessário criar uma nova variável para que o Java não aponte para a mesma variável de memória
        Schedule fakeSchedule2 = createFakeScheduleData();
        Schedule savedSchedule = scheduleRepository.save(fakeSchedule);

        LocalDate startDate = LocalDate.of(2222,11,1);
        savedSchedule.setStartDate(startDate);
        Schedule updatedSchedule = scheduleRepository.save(savedSchedule);

        Assertions.assertThat(updatedSchedule.getStartDate()).isNotEqualTo(fakeSchedule2.getStartDate());
    }

    @Test
    @DisplayName("Delete Schedules")
    void delete_Schedules(){
        Schedule fakeSchedule = createFakeScheduleData();
        Schedule savedSchedule = scheduleRepository.save(fakeSchedule);

        scheduleRepository.delete(savedSchedule);

        Optional<Schedule> nullSchedule = scheduleRepository.findById(savedSchedule.getScheduleId());

        Assertions.assertThat(nullSchedule).isEmpty();
    }
}
