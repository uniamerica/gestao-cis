package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTO.ProfessionalResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.enums.SpecialtyEntity;
import com.gestaocis.backend.models.Address;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.SpecialtyEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ProfessionalService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private SpecialtyEntityRepository specialtyEntityRepository;

    public ProfessionalResponseDTO create(ProfessionalRequestDTO professionalRequestDTO){
        try {
            List<SpecialtyEntity> specialtyEntities = new ArrayList<>();


                professionalRequestDTO.getSpecialties().forEach(uuid -> {
                    SpecialtyEntity specialtyEntity = specialtyEntityRepository.findByUuid(UUID.fromString(uuid)).get();
                        specialtyEntities.add(specialtyEntity);
            });


            String encode = new BCryptPasswordEncoder().encode(professionalRequestDTO.getPassword());

            RoleEntity role = roleEntityRepository
                    .findByRoleName(Role.ROLE_PROFESSIONAL)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            User build = User.builder()
                    .uuid(UUID.randomUUID())
                    .cpf(professionalRequestDTO.getCpf())
                    .rg(professionalRequestDTO.getRg())
                    .email(professionalRequestDTO.getEmail())
                    .phone(professionalRequestDTO.getPhone())
                    .birthdate(professionalRequestDTO.getBirthdate())
                    .sex(professionalRequestDTO.getSex())
                    .addressCountry(professionalRequestDTO.getAddressCountry())
                    .addressLine2(professionalRequestDTO.getAddressLine2())
                    .fullName(professionalRequestDTO.getFullName())
                    .password(encode)
                    .specialties(specialtyEntities)
                    .role(role)
                    .build();


            User save = userRepository.save(build);

            return new ProfessionalResponseDTO(save);
        }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
