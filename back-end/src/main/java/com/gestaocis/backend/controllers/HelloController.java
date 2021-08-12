package com.gestaocis.backend.controllers;

import com.gestaocis.backend.models.Hello;
import com.gestaocis.backend.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping
    public ResponseEntity<List<Hello>> listAllHellos(){
        return new ResponseEntity<>(helloService.index(), HttpStatus.OK);
    }

}
