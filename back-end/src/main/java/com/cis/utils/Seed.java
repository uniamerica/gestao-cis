package com.cis.utils;

import com.cis.model.Admin;
import com.cis.model.HealthProfessional;
import com.cis.model.Room;
import com.cis.model.Specialty;
import com.cis.repository.AdminRepository;
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
    private final AdminRepository adminRepository;

    public Seed(SpecialtyRepository specialtyRepository, RoomRepository roomRepository, HealthProfessionalRepository healthProfessionalRepository, AdminRepository adminRepository) {
        this.specialtyRepository = specialtyRepository;
        this.roomRepository = roomRepository;
        this.healthProfessionalRepository = healthProfessionalRepository;
        this.adminRepository = adminRepository;
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

        Admin adminToBeSaved = new Admin();
        adminToBeSaved.setName("CIS ADMIN");
        adminToBeSaved.setPhone("45789456789");
        adminToBeSaved.setEmail("admin@cis.com.br");
        adminToBeSaved.setRole("ROLE_ADMIN");
        adminToBeSaved.setPassword((new BCryptPasswordEncoder().encode("admin12345")));
        adminToBeSaved.setActive(true);

        adminRepository.save(adminToBeSaved);
        healthProfessionalRepository.saveAll(professionalsToBeSaved);
        roomRepository.saveAll(rooms);
    }

}
