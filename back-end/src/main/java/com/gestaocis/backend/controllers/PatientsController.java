package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.User;
import com.gestaocis.backend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/pacientes")
public class PatientsController {
    private final PatientService service;

    @Autowired
    public PatientsController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody User patient) throws Exception {
        try{
            User saved = service.create(patient);
            return new ResponseEntity<>(saved, null, HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try{
            List<User> found = service.findAll();
            if(!found.isEmpty()){
                return new ResponseEntity<>(found, null, HttpStatus.OK);
            } else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        try{
            User found = service.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User patient) throws Exception {
        try{
            if(service.findById(id) != null) {
                patient.setId(id);
                return new ResponseEntity<>(service.update(patient), null, HttpStatus.OK);
            } else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try{
            if(service.findById(id) != null) {
                service.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            } else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
}
