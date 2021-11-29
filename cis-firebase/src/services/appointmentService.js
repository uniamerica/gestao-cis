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

  // FIND BY ?
  // IMPLEMENTATION

  // CREATE - VALIDATION BY?
  // create: async function(obj) {
  //  try {
  //   const validData = appointmentSchema.validate(obj)

  //   if(validData.error) {
  //    return {
  //     error: validData.error
  //    }
  //   }

  //   const appointmentExistById = await this.findById(obj)
  //  } catch (error) {

  //  }
  // }

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
