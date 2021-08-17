package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Secretary;
import com.gestaocis.backend.repositories.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("secretaries")
public class SecretaryController {

    // TODO: APLICAR ISSO DENTRO DA CAMADA SERVICE, POR ENQUANTO ESTÁ DE EXEMPLO.
    @Autowired
    private SecretaryRepository secretaryRepository;

    @GetMapping
    public ResponseEntity<List<Secretary>> listAllSecretaries(){
        try{
            return new ResponseEntity<>(secretaryRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong: " + e.getMessage());
        }
    }
}
