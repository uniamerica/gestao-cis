const { db } = require("../firebase");
const uuid = require("uuid");
const {
  appointmentSchema,
} = require("../models/appointments/appointmentSchema");

const appointmentCollection = db.collection("appointments");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "date", desc) {
    try {
      const { docs } = await appointmentCollection
        .orderBy(`${order}`, !desc ? "asc" : "desc")
        .startAfter(startAfter)
        .limit(limit * 1)
        .get();
      console.log(docs);
      const paginatedData = {
        count: docs.length,
        limit: limit * 1,
        order: order,
        nextPage: docs.length >= limit * 1,
        items: docs.map((d) => d.data()),
      };

      return paginatedData;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY ID
  findById: async function (id) {
    try {
      const snapshot = appointmentCollection.doc(id).get();
      const appointment = snapshot.data();
      if (!appointment) {
        return {
          error: "Appointment not found",
        };
      }
      return appointment;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // // FIND BY HEALTH PROFESSIONAL ID
  // findByHealthProfessionalId: async function (healthProfessionalId) {
  //   try {
  //     const snapshot = await appointmentCollection
  //       .where("healthProfessional.id", "==", healthProfessionalId)
  //       .get();
  //     const appointmentDoc = snapshot.docs;

  //     if (appointmentDoc.length === 0) {
  //       return null;
  //     }

  //     return patientsDoc.map((doc) => doc.data())[0];
  //   } catch (error) {
  //     throw new Error(error.message);
  //   }
  // },

  // // FIND BY PATIENT ID
  // findByPatientId: async function (patientId) {
  //   try {
  //     const snapshot = await appointmentCollection
  //       .where("patient.id", "==", patientId)
  //       .get();
  //     const appointmentDoc = snapshot.docs;

  //     if (appointmentDoc.length === 0) {
  //       return null;
  //     }

  //     return patientsDoc.map((doc) => doc.data())[0];
  //   } catch (error) {
  //     throw new Error(error.message);
  //   }
  // },

  // // FIND BY DATE
  // findByDate: async function (date) {
  //   try {
  //     const snapshot = await appointmentCollection
  //       .where("date", "==", date)
  //       .get();
  //     const appointmentDoc = snapshot.docs;

  //     if (appointmentDoc.length === 0) {
  //       return null;
  //     }

  //     return patientsDoc.map((doc) => doc.data())[0];
  //   } catch (error) {
  //     throw new Error(error.message);
  //   }
  // },

  // // FIND BY TIME
  // findByTime: async function (time) {
  //   try {
  //     const snapshot = await appointmentCollection
  //       .where("time", "==", time)
  //       .get();
  //     const appointmentDoc = snapshot.docs;

  //     if (appointmentDoc.length === 0) {
  //       return null;
  //     }

  //     return patientsDoc.map((doc) => doc.data())[0];
  //   } catch (error) {
  //     throw new Error(error.message);
  //   }
  // },

  // FIND BY BOOKING (?)
  findByBooking: async function (healthProfessionalId, patientId, date, time) {
    try {
      const appointmentCollection = db.collection("appointments");

      const snapshot = appointmentCollection
        .where("patient.id", "==", patientId)
        .where("date", "==", date)
        .where("time", "==", time)
        .where("healthProfessional.id", "==", healthProfessionalId);

      const appointmentDoc = snapshot.doc;

      if (appointmentDoc && appointmentDoc.length === 0) {
        return null;
      }

      if (appointmentDoc && appointmentDoc.length !== 0) {
        return appointmentDoc.map((doc) => doc.data())[0];
      }
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validData = appointmentSchema.validate(obj);

      if (validData.error) {
        return {
          error: validData.error,
        };
      }

      const appointmentExistByBooking = await this.findByBooking(
        obj.healthProfessional.id,
        obj.patient.id,
        obj.date,
        obj.time
      );

      console.log(appointmentExistByBooking);

      if (!!appointmentExistByBooking) {
        return {
          error: "Appointment already registered",
        };
      }

      const id = uuid.v4();
      const docRef = appointmentCollection.doc(id);

      const result = await docRef.set({
        id: id,
        date: obj.date,
        time: obj.time,
        room: obj.room, // make a separate API call in front-end to fill in the room information
        observation: obj.observation,
        patient: obj.patient, // make a separate API call in front-end to fill in the patient information
        healthProfessional: obj.healthProfessional, // make a separate API call in front-end to fill in the healthProfessional information
        price: obj.price,
        paid: obj.paid,
      });

      console.log(result);

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, appointment) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      const updated = {
        id: id,
        date: !!appointment.date ? appointment.date : found.date,
        time: !!appointment.time ? appointment.time : found.time,
        room: !!appointment.room ? appointment.room : found.room,
        observation: !!appointment.observation
          ? appointment.observation
          : observation.room,
        patient: !!appointment.patient
          ? appointment.patient
          : observation.patient,
        healthProfessional: !!appointment.healthProfessional
          ? appointment.healthProfessional
          : observation.healthProfessional,
        price: !!appointment.price ? appointment.price : observation.price,
        paid: !!appointment.paid ? appointment.paid : observation.paid,
      };

      const result = await appointmentCollection.doc(id).set(updated);

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // DELETE
  delete: async function (id) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      const result = await appointmentCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },
};
