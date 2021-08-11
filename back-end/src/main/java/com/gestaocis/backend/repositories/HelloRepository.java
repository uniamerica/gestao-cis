package com.gestaocis.backend.repositories;

import com.gestaocis.backend.models.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {

}
