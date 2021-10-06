package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/salas")
public class RoomController {
    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody User room) throws Exception {
        try{
            User saved = service.create(room);
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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User room) throws Exception {
        try{
            if(service.findById(id) != null) {
                room.setId(id);
                return new ResponseEntity<>(service.update(room), null, HttpStatus.OK);
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
