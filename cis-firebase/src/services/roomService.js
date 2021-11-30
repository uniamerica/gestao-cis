const { db } = require("../firebase");
const uuid = require("uuid");
const { roomSchema } = require("../models/rooms/roomSchema");

const roomCollection = db.collection("rooms");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "name", desc) {
    try {
      const { docs } = await roomCollection
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
      const snapshot = await roomCollection.doc(id).get();
      const room = snapshot.data();
      if (!room) {
        return {
          error: "Room not found",
        };
      }
      return room;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY NAME
  findByName: async function (name) {
    try {
      const snapshot = await roomCollection.where("name", "==", name).get();
      const roomsDoc = snapshot.docs;

      if (roomsDoc.length === 0) {
        return null;
      }

      return roomsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validData = roomSchema.validate(obj);

      if (validData.error) {
        return {
          error: validData.error,
        };
      }

      const roomExistsByName = await this.findByName(obj.name);

      if (!!roomExistsByName) {
        return {
          error: "Room name already registered",
        };
      }

      const id = uuid.v4();
      const docRef = roomCollection.doc(id);

      const result = await docRef.set({
        id: id,
        name: obj.name,
        specialties: obj.specialties,
      });

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, room) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      const updated = {
        id: id,
        name: !!room.name ? room.name : found.name,
        specialties: !!room.specialties ? room.specialties : found.specialties,
      };

      const result = await roomCollection.doc(id).set(updated);

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

      const result = await roomCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new error(error.message);
    }
  },
};
