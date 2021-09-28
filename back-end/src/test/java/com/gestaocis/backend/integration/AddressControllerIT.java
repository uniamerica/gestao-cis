package com.gestaocis.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestaocis.backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class AddressControllerIT {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;
}
