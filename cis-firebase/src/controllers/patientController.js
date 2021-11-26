const patientDTO = require("../models/patients/patientDTO");
const patientService = require("../services/patientService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await patientService.index(startAfter, limit, order, desc);

      res.json(data).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY EMAIL OR USERNAME
  findByEmailOrUsername: async (req, res) => {
    try {
      const { username, email } = req.query;

      if (!username && !email) {
        throw new Error("Username or email not specified");
      }

      if (username && !email) {
        const data = await patientService.findByUsername(username);
        if (!data) {
          throw new Error("Patient not found");
        }
        res.json(data).status(200);
        return;
      }

      if (!username && email) {
        const data = await patientService.findByEmail(email);
        if (!data) {
          throw new Error("Patient not found");
        }
        res.json(data).status(200);
        return;
      }

      const dataByEmail = await patientService.findByEmail(email);

      if (dataByEmail) {
        res.json(patientDTO(dataByEmail)).status(200);
        return;
      }

      const dataByUsername = await patientService.findByUsername(username);

      if (dataByUsername) {
        res.json(patientDTO(dataByUsername)).status(200);
        return;
      }

      if (!dataByUsername && !dataByEmail) {
        throw new Error("Patient not found");
      }
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY ID
  findById: async (res, res) => {
    try {
      const { id } = req.params;
      const data = await patientService.findById(id);

      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json(patientDTO(data)).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // CREATE
  create: async (req, res) => {
    try {
      const data = await patientService.create(req.body);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json({ success: "Created with success" }).status(201);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // SIGN IN
  signIn: async (req, res) => {
    try {
      const { username, password } = req.body;
      const data = await patientService.signIn(username, password);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json({ token: data }).status(201);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  update: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await patientService.delete(id);
      console.log(data);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json({ success: "Deleted with success" }).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
