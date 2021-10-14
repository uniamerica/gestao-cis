package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.ProfessionalDTOs.NewProfessionalRequestDTO;
import com.gestaocis.backend.DTOs.ProfessionalDTOs.ProfessionalResponseDTO;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.services.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/profissionais")
public class ProfessionalController {

    @Autowired
    private ProfessionalService service;

    @Autowired
    public ProfessionalController (ProfessionalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponseDTO> save(@RequestBody NewProfessionalRequestDTO responseBody) throws Exception {
        return new ResponseEntity<>(this.service.save(responseBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<ProfessionalResponseDTO> findProfessionalByUUID (@RequestParam UUID uuid) {
        return new ResponseEntity<>(this.service.findByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping(path = "/{uuid}")
    public final ResponseEntity<ProfessionalResponseDTO> updateProfessional (@RequestParam UUID uuid, @RequestBody NewProfessionalRequestDTO responseBody) {
        try {
            return new ResponseEntity<>(this.service.update(uuid, responseBody), HttpStatus.OK);
        } catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @DeleteMapping(path = "/{uuid}")
    public final ResponseEntity<String> deleteProfessional (@RequestParam UUID uuid){
        if(this.service.delete(uuid)) {
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Fail to delete", HttpStatus.BAD_REQUEST);
        }
    }

}
