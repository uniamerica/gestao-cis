const { db } = require("../firebase");
const uuid = require("uuid");
const {
  specialtiesSchema,
} = require("../models/specialties/specialtiesSchema");

const specialtyCollection = db.collection("specialties");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "title", desc) {
    try {
      const { docs } = await specialtyCollection
        .orderBy(`${order}`, !desc ? "asc" : "desc")
        .startAfter(!startAfter ? "" : startAfter)
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
      const snapshot = await specialtyCollection.doc(id).get();
      const specialty = snapshot.data();
      if (!specialty) {
        return {
          error: "Specialty not found",
        };
      }
      return specialty;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY TITLE
  findByTitle: async function (title) {
    try {
      const snapshot = await specialtyCollection
        .where("title", "==", title)
        .get();
      const specialtyDoc = snapshot.docs;

      if (specialtyDoc.length === 0) {
        return null;
      }

      return specialtyDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validData = specialtiesSchema.validate(obj);

      if (validData.error) {
        return {
          error: validData.error,
        };
      }

      const specialtyExistsByTitle = await this.findByTitle(obj.title);

      if (!!specialtyExistsByTitle) {
        return {
          error: "Specialty title already registered",
        };
      }

      const id = uuid.v4();
      const docRef = specialtyCollection.doc(id);

      const result = await docRef.set({
        id: id,
        title: obj.title,
        description: obj.description,
      });

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, specialty) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      const updated = {
        id: id,
        title: !!specialty.title ? specialty.title : found.title,
        description: !!specialty.description
          ? specialty.description
          : found.description,
      };

      const result = await specialtyCollection.doc(id).set(updated);

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  //DELETE
  delete: async function (id) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      const result = await specialtyCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },
};
