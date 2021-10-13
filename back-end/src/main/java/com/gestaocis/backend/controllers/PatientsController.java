package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pacientes")
public class PatientsController {

    @Autowired
    private PatientService service;

    @Autowired
    public PatientsController(PatientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> save(@RequestBody NewPatientRequestDTO responseBody) throws Exception {
        return new ResponseEntity<>(this.service.save(responseBody), HttpStatus.CREATED);
    }

    @GetMapping(path ="/{uuid}")
    public ResponseEntity<PatientResponseDTO> findPatientByUUID(@RequestParam UUID uuid){
        return new ResponseEntity<>(this.service.findByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping(path= "/{uuid}")
    public final ResponseEntity<PatientResponseDTO> updatePatient(@RequestParam UUID uuid, @RequestBody NewPatientRequestDTO responseBody){
        try{
            return new ResponseEntity<>(this.service.update(uuid, responseBody), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@lPathVariable Long id, @RequestBody User patient) throws Exception {
//        try{
//            if(service.findById(id) != null) {
//                patient.setId(id);
//                return new ResponseEntity<>(service.update(patient), null, HttpStatus.OK);
//            } else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    @DeleteMapping(path = "/{uuid}")
    public final ResponseEntity<String> deletePatient(@RequestParam UUID uuid){
        if(this.service.delete(uuid)){
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Fail to delete",HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
//        try{
//            if(service.findById(id) != null) {
//                service.delete(id);
//                return new ResponseEntity<>(null, null, HttpStatus.OK);
//            } else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
//        }
//        catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
}
