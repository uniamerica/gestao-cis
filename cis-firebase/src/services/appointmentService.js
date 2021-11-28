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
};
