package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomControlles {

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

    // LIST
    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try{
            List<Room> found = roomService.findAll();
            if(!found.isEmpty()){
                return new ResponseEntity<>(found, null, HttpStatus.OK);
            } else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // SHOW BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        try{
            Room found = roomService.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try{
            if(roomService.findById(id) != null) {
                roomService.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            } else return new ResponseEntity<>( null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
}
