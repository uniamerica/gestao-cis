const { db } = require("../firebase");
const uuid = require("uuid");
const {
  healthProfessionalSchema,
} = require("../models/healhProfessionals/healthProfessionalSchema");
const { encryptPassword, comparePassword } = require("../utils/hashUtils");

const healthProfessionalDTO = require("../models/healhProfessionals/healthProfessionalDTO");
const jwtUtils = require("../utils/jwtUtils");

const healthProfessionalCollection = db.collection("healthProfessional");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "username", desc) {
    try {
      const { docs } = await healthProfessionalCollection
        .orderBy(`${order}`, !desc ? "asc" : "desc")
        .startAfter(!startAfter ? "" : startAfter)
        .limit(limit * 1)
        .get();

      const paginatedData = {
        count: docs.length,
        limit: limit * 1,
        order: order,
        nextPage: docs.length >= limit * 1,
        items: docs.map((d) => healthProfessionalDTO(d.data())),
      };

      return paginatedData;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY EMAIL
  findByEmail: async function (email) {
    try {
      const snapshot = await healthProfessionalCollection
        .where("email", "==", email)
        .get();
      const healthProfessionalsDoc = snapshot.docs;

      if (healthProfessionalsDoc.length === 0) {
        return null;
      }

      return healthProfessionalsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY USERNAME
  findByUsername: async function (username) {
    try {
      const snapshot = await healthProfessionalCollection
        .where("username", "==", username)
        .get();
      const healthProfessionalsDoc = snapshot.docs;

      if (healthProfessionalsDoc.length === 0) {
        return null;
      }

      return healthProfessionalsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.messsage);
    }
  },

  // FIND BY ID
  findById: async function (id) {
    try {
      const snapshot = await healthProfessionalCollection.doc(id).get();
      const healthProfessional = snapshot.data();
      if (!healthProfessional) {
        return {
          error: "Health Professional not found",
        };
      }
      return healthProfessional;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // SIGN IN
  signIn: async function (username, password) {
    try {
      const snapshot = await healthProfessionalCollection
        .where("username", "==", username)
        .get();

      const healthProfessionalsDoc = snapshot.docs;

      if (healthProfessionalsDoc.length === 0) {
        return {
          error: "Invalid Username or Password",
        };
      }

      const healthProfessional = healthProfessionalsDoc.map((doc) =>
        doc.data()
      )[0];

      const isPasswordValid = await comparePassword(
        password,
        healthProfessional.password
      );

      if (!isPasswordValid) {
        return {
          error: "Invalid Username or Password",
        };
      }

      const token = jwtUtils.signJWT(
        healthProfessionalDTO(healthProfessional),
        "health_professional"
      );

      return token;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validData = healthProfessionalSchema.validate(obj);

      if (validData.error) {
        return {
          error: validData.error,
        };
      }

      const healthProfessionalExistsByEmail = await this.findByEmail(obj.email);

      if (!!healthProfessionalExistsByEmail) {
        return {
          error: "Email already registered",
        };
      }

      const healthProfessionalExistsByUsername = await this.findByUsername(
        obj.username
      );

      if (!!healthProfessionalExistsByUsername) {
        return {
          error: "Username already registered",
        };
      }

      const passwordHash = await encryptPassword(obj.password);

      const id = uuid.v4();
      const docRef = await healthProfessionalCollection.doc(id);

      const result = await docRef.set({
        id: id,
        email: obj.email,
        name: obj.name,
        username: obj.username,
        password: passwordHash,
        phone: obj.phone,
        professionalDocument: obj.professionalDocument,
        specialty: obj.specialty,
      });

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, healthProfessional) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      var passwordHash;
      if (healthProfessional.password) {
        passwordHash = await encryptPassword(healthProfessional.password);
      }

      const updated = {
        id: id,
        email: !!healthProfessional.email
          ? healthProfessional.email
          : found.email,
        name: !!healthProfessional.name ? healthProfessional.name : found.name,
        username: !!healthProfessional.username
          ? healthProfessional.username
          : found.username,
        password: !!healthProfessional.password ? passwordHash : found.password,
        phone: !!healthProfessional.phone
          ? healthProfessional.phone
          : found.phone,
        specialty: !!healthProfessional.specialty
          ? healthProfessional.specialty
          : found.specialty,
      };

      const result = await healthProfessionalCollection.doc(id).set(updated);

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

      const result = await healthProfessionalCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },
};
