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
public class Secretary extends User{

    private UUID secretaryId;


    // TODO: DESENVOLVER ESSA BAGAÇA
    public void registerPatient(){

    }

    // TODO: DESENVOLVER ESSA BAGAÇA
    public void addNewPatientAppointment(){

    }

    // TODO: DESENVOLVER ESSA BAGAÇA
    public void modifyPatientAppointment(){

    }

    // TODO: DESENVOLVER ESSA BAGAÇA
    public void cancelPatientAppointment(){

    }

    // TODO: DESENVOLVER ESSA BAGAÇA
    public void searchPatientAppointment(){

    }
}
