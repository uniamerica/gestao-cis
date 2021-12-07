package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.HealthProfessional;
import com.cis.model.Room;
import com.cis.model.Schedule;
import com.cis.model.dto.ScheduleDTO.ScheduleRequestDTO;
import com.cis.model.dto.ScheduleDTO.ScheduleResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.repository.RoomRepository;
import com.cis.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final HealthProfessionalRepository healthProfessionalRepository;
    private final RoomRepository roomRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, HealthProfessionalRepository healthProfessionalRepository, RoomRepository roomRepository) {
        this.scheduleRepository = scheduleRepository;
        this.healthProfessionalRepository = healthProfessionalRepository;
        this.roomRepository = roomRepository;
    }

    public Page<ScheduleResponseDTO> index(Pageable pageable){
        try{
            Page<Schedule> all = scheduleRepository.findAll(pageable);
            return all.map(ScheduleResponseDTO::new);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public ScheduleResponseDTO findByID(UUID id){
        try{
            Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new BadRequestException("Schedule not found"));
            return new ScheduleResponseDTO(schedule);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public ScheduleResponseDTO create(ScheduleRequestDTO scheduleRequestDTO){
        try{
            HealthProfessional healthProfessional = healthProfessionalRepository.findById((scheduleRequestDTO.getProfessional())).orElseThrow(() -> new BadRequestException("Health Professional Not Found"));
            Room room = roomRepository.findById(scheduleRequestDTO.getRoom()).orElseThrow(() -> new BadRequestException("Room not Found"));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // VERIFICAR SE PROFISSIONAL JÁ POSSUI AGENDA NESSA DATA E HORA
            List<Schedule> byProfessional = scheduleRepository.findByProfessional(healthProfessional);
            if(!byProfessional.isEmpty()){
                byProfessional.forEach(schedule -> {
                    if(schedule.getDate().equals(scheduleRequestDTO.getDate()) && schedule.getHour().equals(scheduleRequestDTO.getHour()) && schedule.getMinutes().equals(scheduleRequestDTO.getMinutes())) {
                        throw new BadRequestException("Profissional já possui agenda nessa data e hora");
                    }
                });
            }

            // VERIFICAR SE SALA JÁ POSSIU AGENDA NESSA DATA E HORA
            List<Schedule> byRoom = scheduleRepository.findByRoom(room);
            if(!byRoom.isEmpty()){
                byRoom.forEach((schedule -> {
                    if(schedule.getDate().equals(scheduleRequestDTO.getDate()) && schedule.getHour().equals(scheduleRequestDTO.getHour()) && schedule.getMinutes().equals(scheduleRequestDTO.getMinutes())) {
                        throw new BadRequestException("Sala já possui agenda nessa data e hora");
                    }
                }));
            }

            Schedule build = Schedule
                    .builder()
                    .date(scheduleRequestDTO.getDate())
                    .hour(scheduleRequestDTO.getHour())
                    .minutes(scheduleRequestDTO.getMinutes())
                    .professional(healthProfessional)
                    .room(room)
                    .build();

            Schedule save = scheduleRepository.save(build);

            return new ScheduleResponseDTO(save);


        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public ScheduleResponseDTO update(UUID id, ScheduleRequestDTO scheduleRequestDTO){
        try{
            Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new BadRequestException("Schedule not found"));
            HealthProfessional healthProfessional = healthProfessionalRepository.findById((scheduleRequestDTO.getProfessional())).orElseThrow(() -> new BadRequestException("Health Professional Not Found"));
            Room room = roomRepository.findById(scheduleRequestDTO.getRoom()).orElseThrow(() -> new BadRequestException("Room not Found"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(scheduleRequestDTO.getDate());

            schedule.setProfessional(healthProfessional);
            schedule.setRoom(room);
            schedule.setDate(simpleDateFormat.parse(format));

            Schedule save = scheduleRepository.save(schedule);
            return new ScheduleResponseDTO(save);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete (UUID id){
        try{
            Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new BadRequestException("Schedule not found"));
            scheduleRepository.delete(schedule);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteAllByProfessional(UUID professionalId){
        HealthProfessional healthProfessional = healthProfessionalRepository.findById((professionalId)).orElseThrow(() -> new BadRequestException("Health Professional Not Found"));
        List<Schedule> byProfessional = scheduleRepository.findByProfessional(healthProfessional);
        scheduleRepository.deleteAll(byProfessional);
    }

    public void deleteAllByRoom(UUID roomId){
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new BadRequestException("Room not Found"));
        List<Schedule> byRoom = scheduleRepository.findByRoom(room);
        scheduleRepository.deleteAll(byRoom);

    }
}
