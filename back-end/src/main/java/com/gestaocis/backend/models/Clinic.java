package com.gestaocis.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    private UUID clinicId;
    private String name;
    private String address;

    public void CreateClinic(){

    }
    public void EditClinic(){

    }
    public void DeleteClinic(){

    }
    public void ListClinic(){

    }

}
