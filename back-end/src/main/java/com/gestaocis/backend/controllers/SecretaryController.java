package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.services.SecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/admin/secretary")
public class SecretaryController {

    @Autowired private SecretaryService secretaryService;

    @PostMapping
    public ResponseEntity<SecretaryResponseDTO> save(@RequestBody NewSecretaryRequestDTO responseBody) throws Exception {
        return new ResponseEntity<>(this.secretaryService.save(responseBody), HttpStatus.CREATED);
    }

    @GetMapping(path ="/{uuid}")
    public ResponseEntity<SecretaryResponseDTO> findSecretaryByUUID(@RequestParam UUID uuid){
        return new ResponseEntity<>(this.secretaryService.findByUUID(uuid), HttpStatus.OK);
    }

}
