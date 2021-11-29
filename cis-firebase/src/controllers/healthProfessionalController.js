const healthProfessionalDTO = require("../models/healthProfessionals/healthProfessionalDTO");
const healthProfessionalService = require("../services/healthProfessionalService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await healthProfessionalService.index(
        startAfter,
        limit,
        order,
        desc
      );

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
        const data = await healthProfessionalService.findByUsername(username);
        if (!data) {
          throw new Error("Professional not found");
        }
        res.status(200).json(healthProfessionalDTO(data));
        return;
      }

      if (!username && email) {
        const data = await healthProfessionalService.findByEmail(email);
        if (!data) {
          throw new Error("Professional not found");
        }
        res.status(200).json(healthProfessionalDTO(data));
        return;
      }

      const dataByEmail = await healthProfessionalService.findByEmail(email);

      if (dataByEmail) {
        res.status(200).json(healthProfessionalDTO(dataByEmail));
        return;
      }

      const dataByUsername = await healthProfessionalService.findByUsername(
        username
      );

      if (dataByUsername) {
        res.status(200).json(healthProfessionalDTO(dataByUsername));
        return;
      }

      if (!dataByUsername && !dataByEmail) {
        throw new Error("Professional not found");
      }
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY ID
  findById: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await healthProfessionalService.findById(id);

      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json(healthProfessionalDTO(data));
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // CREATE
  create: async (req, res) => {
    try {
      const data = await healthProfessionalService.create(req.body);
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
      const data = await healthProfessionalService.signIn(username, password);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(201).json({ token: data });
      return;
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // UPDATE
  update: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await healthProfessionalService.update(id, req.body);
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
      const data = await healthProfessionalService.delete(id);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json({ success: "Successfully deleted!" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
