package com.cis.service;

import com.cis.exceptions.BadRequestException;
import com.cis.model.Admin;
import com.cis.model.HealthProfessional;
import com.cis.model.dto.AdminDTO.AdminCreationDTO;
import com.cis.model.dto.AdminDTO.AdminReturnDTO;
import com.cis.model.dto.AdminDTO.AdminUpdateDTO;
import com.cis.model.dto.HeathProfessionalDTO.HealthProfessionalResponseDTO;
import com.cis.repository.AdminRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Email or Password"));
    }

    // FIND BY ID
    public AdminReturnDTO findByIdOrThrowError(UUID id) {
        Admin admin = repository.findById(id).orElseThrow(() -> new BadRequestException("Admin Not Found"));
        return new AdminReturnDTO(admin);
    }

    // FIND BY EMAIL
    public Optional<Admin> findByEmailOrThrowError(String email) {

        Optional<Admin> foundAdmin = repository.findByEmail(email);

        if (foundAdmin.isPresent()) {
            return foundAdmin;
        } else {
            throw new BadRequestException("Admin não encontrado.");
        }
    }

    // CREATE
    @Transactional
    public AdminReturnDTO save(AdminCreationDTO adminCreationDTO) {
        try {
            Optional<Admin> findAdmin = repository.findByEmail(adminCreationDTO.getEmail());
            if (findAdmin.isPresent()) {
                throw new BadRequestException("Email já cadastrado");
            }
            Admin adminToBeSaved = new Admin();
            adminToBeSaved.setId(UUID.randomUUID());
            adminToBeSaved.setName(adminCreationDTO.getName());
            adminToBeSaved.setPhone(adminCreationDTO.getPhone());
            adminToBeSaved.setEmail(adminCreationDTO.getEmail());
            adminToBeSaved.setRole("ROLE_ADMIN");
            adminToBeSaved.setPassword((new BCryptPasswordEncoder().encode(adminCreationDTO.getPassword())));
            adminToBeSaved.setActive(true);

            Admin save = repository.save(adminToBeSaved);
            return new AdminReturnDTO(save);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // LIST
    public Page<AdminReturnDTO> listAll(Pageable pageable) {
        Page<Admin> all = repository.findAll(pageable);
        return all.map(AdminReturnDTO::new);
    }

    // UPDATE
    public String update(UUID id, AdminUpdateDTO admin) {

        Admin savedAdmin = repository.getById(id);

        savedAdmin.setName(admin.getName());
        savedAdmin.setEmail(admin.getEmail());
        savedAdmin.setPhone(admin.getPhone());

        repository.save(savedAdmin);
        return "Cadastro atualizado com sucesso!";
    }

    //DELETE
    public String delete(UUID id) {
        Admin admin = repository.findById(id).orElseThrow(() -> new BadRequestException("User Not Found"));
        repository.delete(admin);
        return "Admin deletado com sucesso!";
    }
}
