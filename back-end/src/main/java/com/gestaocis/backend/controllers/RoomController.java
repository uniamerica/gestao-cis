package com.gestaocis.backend.controllers;

import com.gestaocis.backend.DTOs.SecretaryDTOs.NewSecretaryRequestDTO;
import com.gestaocis.backend.DTOs.SecretaryDTOs.SecretaryResponseDTO;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody Room room) throws Exception {
        try{
            Room saved = roomService.create(room);
            return new ResponseEntity<>(saved, null, HttpStatus.CREATED);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // LIST ALL
    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try{
            List<Room> found = roomService.findAll();
                return new ResponseEntity<>(found, null, HttpStatus.OK);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // SHOW BY UUID
    @GetMapping("/id/{uuid}")
    public ResponseEntity<?> findById(@PathVariable UUID uuid) throws Exception {
        try{
            Room found = roomService.findByUuid(uuid);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // UPDATE (BY UUID)
    @PutMapping("/id/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody Room room) throws Exception {
        try{
            if(roomService.findByUuid(uuid) != null) {
                room.setUuid(uuid);
                return new ResponseEntity<>( roomService.update(room), null, HttpStatus.OK);
            } else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);

        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // DELETE (BY UUID)
    @DeleteMapping(path = "/{uuid}")
    public final ResponseEntity<String> deleteRoom(@PathVariable UUID uuid) {
        if (this.roomService.delete(uuid)) {
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
        }
    }
}
