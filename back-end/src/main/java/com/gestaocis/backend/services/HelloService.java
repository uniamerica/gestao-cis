package com.gestaocis.backend.services;

import com.gestaocis.backend.models.Hello;
import com.gestaocis.backend.repositories.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HelloService {

    @Autowired
    private HelloRepository helloRepository;

    public List<Hello> index(){
        try{
            return helloRepository.findAll();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
