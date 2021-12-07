package com.cis.service;

import com.cis.model.Schedule;
import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.repository.RoomRepository;
import com.cis.repository.ScheduleRepository;
import com.cis.utils.HealthProfessionalCreator;
import com.cis.utils.RoomCreator;
import com.cis.utils.ScheduleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private HealthProfessionalRepository healthProfessionalRepository;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    void setup(){
        BDDMockito.when(healthProfessionalRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));

        BDDMockito.when(roomRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(RoomCreator.createRoomSaved()));
    }


    @Test
    @DisplayName("Should create a schedule when successful")
    public void test_create(){
        BDDMockito.when(scheduleRepository.findByProfessional(ArgumentMatchers.any()))
                .thenReturn(List.of());

        BDDMockito.when(scheduleRepository.findByRoom(ArgumentMatchers.any()))
                .thenReturn(List.of());

        BDDMockito.when(scheduleRepository.save(ArgumentMatchers.any()))
                .thenReturn(ScheduleCreator.createValidScheduleSaved());

        ScheduleResponseDTO scheduleResponseDTO = scheduleService.create(ScheduleCreator.createValidScheduleRequestDTOtoBeSaved());

        Assertions.assertThat(scheduleResponseDTO).isNotNull();
        Assertions.assertThat(scheduleResponseDTO.getProfessional().getEmail()).isEqualTo(HealthProfessionalCreator.createHealthProfessionalSaved().getEmail());
        Assertions.assertThat(scheduleResponseDTO.getRoom().getRoomNumber()).isEqualTo(RoomCreator.createRoomSaved().getRoomNumber());
    }

    @Test
    @DisplayName("Should return Schedule by Id when successful")
    public void test_findByID(){
        BDDMockito.when(scheduleRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ScheduleCreator.createValidScheduleSaved()));

        ScheduleResponseDTO byID = scheduleService.findByID(UUID.randomUUID());
        Assertions.assertThat(byID).isNotNull();

    }

    @Test
    @DisplayName("Should return list of schedule when successful")
    public void test_index(){
        PageImpl<Schedule> schedulePage = new PageImpl<>(List.of(ScheduleCreator.createValidScheduleSaved()));

        BDDMockito.when(scheduleRepository.findAll((Pageable) ArgumentMatchers.any()))
                .thenReturn(schedulePage);

        Page<ScheduleResponseDTO> index = scheduleService.index(null);

        Assertions.assertThat(index).isNotNull();

        Assertions.assertThat(index.toList())
                .hasSize(1)
                .isNotNull();


    }

    @Test
    @DisplayName("Should update schedule when successful")
    public void test_update(){

        Schedule toBeUpdated = ScheduleCreator.createValidScheduleSaved();
        toBeUpdated.setHour(8);

        BDDMockito.when(scheduleRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ScheduleCreator.createValidScheduleSaved()));

        BDDMockito.when(healthProfessionalRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(HealthProfessionalCreator.createHealthProfessionalSaved()));

        BDDMockito.when(roomRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(RoomCreator.createRoomSaved()));

        BDDMockito.when(scheduleRepository.save(ArgumentMatchers.any()))
                .thenReturn(toBeUpdated);


        ScheduleResponseDTO update = scheduleService.update(UUID.randomUUID(), ScheduleCreator.createValidScheduleRequestDTOtoBeSaved());
        Assertions.assertThat(update).isNotNull();
        Assertions.assertThat(update.getHour()).isEqualTo(toBeUpdated.getHour());

    }

    @Test
    @DisplayName("Should delete schedule when successful")
    public void test_delete(){
        BDDMockito.when(scheduleRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ScheduleCreator.createValidScheduleSaved()));

        BDDMockito.doNothing().when(scheduleRepository).delete(ArgumentMatchers.any());

        scheduleService.delete(UUID.randomUUID());
    }


}