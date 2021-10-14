package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.RoomDTOs.NewRoomRequestDTO;
import com.gestaocis.backend.DTOs.RoomDTOs.RoomResponseDTO;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.UserRepository;
import com.gestaocis.backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Service
public class RoomController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;
    private RoomService service;


    @PostMapping //nova sala
    public ResponseEntity<RoomResponseDTO> save(@RequestBody NewRoomRequestDTO responseBody) throws Exception {
        return new ResponseEntity<>(this.service.save(responseBody), HttpStatus.CREATED);
    }

    @GetMapping(path ="/{uuid}") // UUID= "unicos"
    public ResponseEntity<RoomResponseDTO> findRoomByUUID(@RequestParam UUID uuid){
        return new ResponseEntity<>(this.service.findByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping(path= "/{uuid}")
    public final ResponseEntity<RoomResponseDTO> updatePatient(@RequestParam UUID uuid, @RequestBody NewRoomRequestDTO responseBody){
        try{
            return new ResponseEntity<>(this.service.update(uuid, responseBody), HttpStatus.OK);
        }catch (Exception exception){
            throw new BadRequestException(exception.getMessage());
        }
    }

    @DeleteMapping(path = "/{uuid}")
    public final ResponseEntity<String> deleteRoom(@RequestParam UUID uuid){
        if(this.service.delete(uuid)){
            return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Fail to delete",HttpStatus.BAD_REQUEST);
        }
    }
