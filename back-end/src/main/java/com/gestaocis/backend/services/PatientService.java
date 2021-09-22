package com.gestaocis.backend.services;

import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repository;

    @Autowired
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public User create(User patient) {
        return repository.save(patient);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> found = repository.findById(id);

        if(found.isPresent()) return found.get();
        else return null;
    }

    public User update(User patient) {
        return repository.save(patient);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
