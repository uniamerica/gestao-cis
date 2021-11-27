const { db } = require("../firebase");
const uuid = require("uuid");
const { adminSchema } = require("../models/admins/adminSchema");
const { encryptPassword, comparePassword } = require("../utils/hashUtils");

const adminDTO = require("../models/admins/adminDTO");
const jwtUtils = require("../utils/jwtUtils");

const adminCollection = db.collection("admins");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "username", desc) {
    try {
      const { docs } = await adminCollection
        .orderBy(`${order}`, !desc ? "asc" : "desc")
        .startAfter(!startAfter ? "" : startAfter)
        .limit(limit * 1)
        .get();

      const paginatedData = {
        count: docs.length,
        limit: limit * 1,
        order: order,
        nextPage: docs.length >= limit * 1,
        items: docs.map((d) => adminDTO(d.data())),
      };

      return paginatedData;
    } catch (error) {
      throw new Error(error.message);
    }
  },
  // FIND BY EMAIL
  findByEmail: async function (email) {
    try {
      const snapshot = await adminCollection.where("email", "==", email).get();
      const adminsDoc = snapshot.docs;

      if (adminsDoc.length === 0) {
        return null;
      }

      return adminsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY USERNAME
  findByUsername: async function (username) {
    try {
      const snapshot = await adminCollection
        .where("username", "==", username)
        .get();
      const adminsDoc = snapshot.docs;

      if (adminsDoc.length === 0) {
        return null;
      }

      return adminsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY ID
  findByid: async function (id) {
    try {
      const snapshot = await adminCollection.doc(id).get();
      const admin = snapshot.data();
      if (!admin) {
        return {
          error: "Admin Not Found!",
        };
      }
      return admin;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // TODO: CHANGE PASSWORD

  // SIGN IN
  signIn: async function (username, password) {
    try {
      const snapshot = await adminCollection
        .where("username", "==", username)
        .get();

      const adminsDoc = snapshot.docs;

      if (adminsDoc.length === 0) {
        return {
          error: "Invalid Username or Password!",
        };
      }

      const admin = adminsDoc.map((doc) => doc.data())[0];

      const isPasswordValid = await comparePassword(password, admin.password);

      if (!isPasswordValid) {
        return {
          error: "Invalid Username or Password!",
        };
      }

      const token = jwtUtils.signJWT(adminDTO(admin), "admin");

      return token;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validDate = adminSchema.validate(obj);

      if (validDate.error) {
        return {
          error: validDate.error,
        };
      }

      const adminExistsByEmail = await this.findByEmail(obj.email);

      if (!!adminExistsByEmail) {
        return {
          error: "Email already registered",
        };
      }

      const adminExistsByUsername = await this.findByUsername(obj.username);

      if (!!adminExistsByUsername) {
        return {
          error: "Username already registered",
        };
      }

      const passwordHash = await encryptPassword(obj.password);

      const id = uuid.v4();
      const docRef = adminCollection.doc(id);

      const result = await docRef.set({
        id: id,
        role: "admin",
        name: obj.name,
        email: obj.email,
        password: passwordHash,
        phone: obj.phone,
        username: obj.username,
      });

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, admin) {
    try {
      const found = await this.findByid(id);
      if (!!found.error) return found;

      if (admin.password) {
        const passwordHash = await encryptPassword(admin.password);
      }

      const updated = {
        name: !!admin.name ? admin.name : found.name,
        email: !!admin.email ? admin.email : found.email,
        password: found.password,
        phone: !!admin.phone ? admin.phone : found.phone,
        username: !!admin.username ? passwordHash : found.username,
      };

      const result = await adminCollection.doc(id).set(updated);

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // DELETE
  delete: async function (id) {
    try {
      const found = await this.findByid(id);
      if (!!found.error) return found;

      const result = await adminCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },
};
