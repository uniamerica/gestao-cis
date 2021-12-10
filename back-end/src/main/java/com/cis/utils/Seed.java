package com.cis.utils;

import com.cis.model.HealthProfessional;
import com.cis.model.Room;
import com.cis.model.Specialty;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalCreationDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.repository.HealthProfessionalRepository;
import com.cis.repository.RoomRepository;
import com.cis.repository.SpecialtyRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class Seed {

    private final SpecialtyRepository specialtyRepository;
    private final RoomRepository roomRepository;
    private final HealthProfessionalRepository healthProfessionalRepository;

    public Seed(SpecialtyRepository specialtyRepository, RoomRepository roomRepository, HealthProfessionalRepository healthProfessionalRepository) {
        this.specialtyRepository = specialtyRepository;
        this.roomRepository = roomRepository;
        this.healthProfessionalRepository = healthProfessionalRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public final void seed(){
        List<String> specialtiesString = List.of(
                "Psicologia",
                "Nutrição",
                "Fisioterapia",
                "Psiquiatria",
                "Acupuntura",
                "Massagem Terapêutica",
                "Psicopedagogia",
                "Fonoaudiologia",
                "Estética facial e corporal"
        );

        List<String> roomsString = List.of(
                "Sala 01",
                "Sala 02",
                "Sala 03",
                "sala 04",
                "sala 05",
                "Sala 06",
                "Sala 07",
                "Sala 08",
                "Sala 09"
        );

        List<Specialty> specialties = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<HealthProfessional> professionalsToBeSaved = new ArrayList<>();

        specialtiesString.forEach(s -> {
            Specialty build = Specialty.builder().name(s).build();
            specialties.add(build);
        });

        List<Specialty> specialtiesSaved = specialtyRepository.saveAll(specialties);

        roomsString.forEach(r -> {
            int i = roomsString.indexOf(r);
            Room build = Room.builder().roomNumber(r).specialties(List.of(specialtiesSaved.get(i))).build();

            HealthProfessional professionalToBeSaved = new HealthProfessional();
            professionalToBeSaved.setProfessional_id(UUID.randomUUID());
            professionalToBeSaved.setName("profissional de " + specialtiesSaved.get(i).getName());
            professionalToBeSaved.setPhone("9999999" +i);
            professionalToBeSaved.setProfessionalDocument("80808080" + i);
            professionalToBeSaved.setEmail("profissional_"+i+"@gmail.com");
            professionalToBeSaved.setRole("ROLE_PROFESSIONAL");
            professionalToBeSaved.setSpecialty(specialtiesSaved.get(i));
            professionalToBeSaved.setPassword(new BCryptPasswordEncoder().encode("profissional"+i));
            professionalToBeSaved.setActive(true);

            professionalsToBeSaved.add(professionalToBeSaved);

            rooms.add(build);
        });

        healthProfessionalRepository.saveAll(professionalsToBeSaved);
        roomRepository.saveAll(rooms);
    }

}
