package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.PatientDTOs.NewPatientRequestDTO;
import com.gestaocis.backend.DTOs.PatientDTOs.PatientResponseDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
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
    private PatientService patientService;

    @PostMapping
    public final ResponseEntity<PatientResponseDTO> save(@RequestBody NewPatientRequestDTO responseBody) throws Exception {
        try{
            return new ResponseEntity<>(this.patientService.save(responseBody), HttpStatus.CREATED);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping
    public final ResponseEntity<List<PatientResponseDTO>> findAll(){
        try{
            return new ResponseEntity<>(this.patientService.findAll(), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping(path ="/id/{uuid}")
    public final ResponseEntity<PatientResponseDTO> findPatientByUUID(@PathVariable UUID uuid){
        try{
            return new ResponseEntity<>(this.patientService.findByUUID(uuid), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping(path = "/email/{email}")
    public final ResponseEntity<PatientResponseDTO> findPatientByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(this.patientService.findByEmail(email), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping(path="/name/{name}")
    public final ResponseEntity<List<PatientResponseDTO>> findListOfPatientsByFullName(@PathVariable String name){
        try{
            return new ResponseEntity<>(this.patientService.findByFullName(name), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @GetMapping(path = "/cpf/{cpf}")
    public final ResponseEntity<PatientResponseDTO> findPatientByCpf(@PathVariable String cpf){
        try{
            return new ResponseEntity<>(this.patientService.findByCpf(cpf), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @PutMapping(path= "/{uuid}")
    public final ResponseEntity<PatientResponseDTO> updatePatient(@RequestParam UUID uuid, @RequestBody NewPatientRequestDTO responseBody){
        try{
            return new ResponseEntity<>(this.patientService.update(uuid, responseBody), HttpStatus.OK);
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
        if(this.patientService.delete(uuid)){
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
