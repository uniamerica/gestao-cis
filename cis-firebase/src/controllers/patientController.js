const patientDTO = require("../models/patients/patientDTO");
const patientService = require("../services/patientService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await patientService.index(startAfter, limit, order, desc);

      res.status(200).json(data);
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
        res.status(200).json(data);
        return;
      }

      if (!username && email) {
        const data = await patientService.findByEmail(email);
        if (!data) {
          throw new Error("Patient not found");
        }
        res.status(200).json(data);
        return;
      }

      const dataByEmail = await patientService.findByEmail(email);

      if (dataByEmail) {
        res.status(200).json(patientDTO(dataByEmail));
        return;
      }

      const dataByUsername = await patientService.findByUsername(username);

      if (dataByUsername) {
        res.status(200).json(patientDTO(dataByUsername));
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
  findById: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await patientService.findById(id);

      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json(patientDTO(data));
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

      res.status(201).json({ success: "Created with success" });
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

      res.status(201).json({ token: data });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // UPDATE
  update: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await patientService.update(id, req.body);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json({ success: "Record successfully updated!" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // DELETE
  delete: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await patientService.delete(id);
      console.log(data);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json({ success: "Successfully deleted!" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
