// package com.cis.utils;
//
// import com.cis.model.Appointment;
// import com.cis.model.dto.AppointmentDTO.AppointmentRequestDTO;
//
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.UUID;
//
// public class AppointmentCreator {
//  public static AppointmentRequestDTO appointmentToSave() throws ParseException {
//    return AppointmentRequestDTO.builder()
//        .date(new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-17"))
//        .hour(12)
//        .minute(30)
//        .professionalId(UUID.randomUUID())
//        .patientId(UUID.randomUUID())
//        .roomId(UUID.randomUUID())
//        .observation("Oh benção, funciona por favor")
//        .paid(false)
//        .build();
//  }
//
//  public static Appointment appointmentSaved() throws ParseException {
//
//    Appointment appointment = new Appointment();
//    appointment.setId(UUID.randomUUID());
//    appointment.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-17"));
//    appointment.setHour(11);
//    appointment.setMinute(30);
//    appointment.setObservation("Funcione Teste por Gentileza");
//
//    return appointment;
//    // appointment.setProfessional(HealthProfessional.);
//    // ^ Guys, essa porra não tá funcionando e eu não sei como prosseguir, sorry S2
//  }
// }
