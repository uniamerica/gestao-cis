const { db } = require("../firebase");
const uuid = require("uuid");
const { patientSchema } = require("../models/patients/patientSchema");
const { encryptPassword, comparePassword } = require("../utils/hashUtils");

const patientDTO = require("../models/patients/patientDTO");
const jwtUtils = require("../utils/jwtUtils");

const patientCollection = db.collection("patients");

module.exports = {
  // LIST ALL
  index: async function (startAfter, limit = 10, order = "username", desc) {
    try {
      const { docs } = await patientCollection
        .orderBy(`${order}`, !desc ? "asc" : "desc")
        .startAfter(!startAfter ? "" : startAfter)
        .limit(limit * 1)
        .get();

      const paginatedData = {
        count: docs.length,
        limit: limit * 1,
        order: order,
        nextPage: docs.length >= limit * 1,
        items: docs.map((d) => patientDTO(d.data())),
      };

      return paginatedData;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY EMAIL
  findByEmail: async function (email) {
    try {
      const snapshot = await patientCollection
        .where("email", "==", email)
        .get();
      const patientsDoc = snapshot.docs;

      if (patientsDoc.length === 0) {
        return null;
      }

      return patientsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY USERNAME
  findByUsername: async function (username) {
    try {
      const snapshot = await patientCollection
        .where("username", "==", username)
        .get();
      const patientsDoc = snapshot.docs;

      if (patientsDoc.length === 0) {
        return null;
      }

      return patientsDoc.map((doc) => doc.data())[0];
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // FIND BY ID
  findById: async function (id) {
    try {
      const snapshot = await patientCollection.doc(id).get();
      const patient = snapshot.data();
      if (!patient) {
        return {
          error: "Patient not found!",
        };
      }
      return patient;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // TODO: CHANGE PASSWORD
  // passwordChange: async function (id, password) {
  //   try {
  //     const snapshot = this.findById(id);
  //     const patientDoc = snapshot.data();

  //     if(patientDoc.password === password) {
  //       return {
  //         error: "Password must be different from current password!"
  //       }
  //     }

  //   }
  // }

  // SIGN IN
  signIn: async function (username, password) {
    try {
      const snapshot = await patientCollection
        .where("username", "==", username)
        .get();

      const patientsDoc = snapshot.docs;

      if (patientsDoc.length === 0) {
        return {
          error: "Invalid Username or Password!",
        };
      }

      const patient = patientsDoc.map((doc) => doc.data())[0];

      const isPasswordValid = await comparePassword(password, patient.password);

      if (!isPasswordValid) {
        return {
          error: "Invalid Username or Password!",
        };
      }

      const token = jwtUtils.signJWT(patientDTO(patient), "patient");

      return token;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async function (obj) {
    try {
      const validData = patientSchema.validate(obj);

      if (validData.error) {
        return {
          error: validData.error,
        };
      }

      const patientExistsByEmail = await this.findByEmail(obj.email);

      if (!!patientExistsByEmail) {
        return {
          error: "Email already registered",
        };
      }

      const patientExistsByUsername = await this.findByUsername(obj.username);

      if (!!patientExistsByUsername) {
        return {
          error: "Username already registered",
        };
      }

      const passwordHash = await encryptPassword(obj.password);

      const id = uuid.v4();
      const docRef = patientCollection.doc(id);

      const result = await docRef.set({
        id: id,
        cpf: obj.cpf,
        rg: obj.rg,
        name: obj.name,
        email: obj.email,
        username: obj.username,
        motherName: obj.motherName,
        phone: obj.phone,
        weight: obj.weight,
        birthdate: obj.birthdate,
        biologicalSex: obj.biologicalSex,
        password: passwordHash,
        address: obj.address,
      });

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // UPDATE
  update: async function (id, patient) {
    try {
      const found = await this.findById(id);
      if (!!found.error) return found;

      var passwordHash;
      if (patient.password) {
        passwordHash = await encryptPassword(patient.password);
      }

      const updated = {
        id: id,
        cpf: !!patient.cpf ? patient.cpf : found.cpf,
        rg: !!patient.rg ? patient.rg : found.rg,
        name: !!patient.name ? patient.name : found.name,
        email: !!patient.email ? patient.email : found.email,
        username: !!patient.username ? patient.username : found.username,
        motherName: !!patient.motherName
          ? patient.motherName
          : found.motherName,
        phone: !!patient.phone ? patient.phone : found.phone,
        weight: !!patient.weight ? patient.weight : found.weight,
        birthdate: !!patient.birthdate ? patient.birthdate : found.birthdate,
        biologicalSex: !!patient.biologicalSex
          ? patient.biologicalSex
          : found.biologicalSex,
        password: !!patient.password ? passwordHash : found.password,
        address: !!patient.address ? patient.address : found.address,
      };

      const result = await patientCollection.doc(id).set(updated);

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

      const result = await patientCollection.doc(id).delete();

      return result;
    } catch (error) {
      throw new Error(error.message);
    }
  },
};
