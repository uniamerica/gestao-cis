const adminDTO = require("../models/admins/adminDTO");
const adminService = require("../services/adminService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await adminService.index(startAfter, limit, order, desc);

      res.status(200).json(data);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY EMAIL OR USERNAME
  findByEmailOrUsename: async (req, res) => {
    try {
      const { username, email } = req.query;

      if (!username && !email) {
        throw new Error("username or email not specified");
      }

      if (username && !email) {
        const data = await adminService.findByUsername(username);
        if (!data) {
          throw new Error("admin not found");
        }
        res.status(200).json(data);
        return;
      }

      if (!username && email) {
        const data = await adminService.findByEmail(email);
        if (!data) {
          throw new Error("admin not found");
        }
        res.status(200).json(data);
        return;
      }

      const dataByEmail = await adminService.findByEmail(email);

      if (dataByEmail) {
        res.status(200).json(adminDTO(dataByEmail));
        return;
      }

      const dataByUsername = await adminService.findByUsername(username);

      if (dataByUsername) {
        res.status(200).json(adminDTO(dataByUsername));
        return;
      }

      if (!dataByUsername && !dataByEmail) {
        throw new Error("admin not found");
      }
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  //FIND BY ID
  findById: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await adminService.findByid(id);

      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json(adminDTO(data));
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // CREATE
  create: async (req, res) => {
    try {
      const data = await adminService.create(req.body);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(201).json({ success: "create with success" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // SIGN IN
  signIn: async (req, res) => {
    try {
      const { username, password } = req.body;
      const data = await adminService.signIn(username, password);
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
      const data = await adminService.update(id, req.body);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json({ success: "updated with success" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
  // DELETE
  delete: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await adminService.delete(id);
      console.log(data);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.status(200).json({ success: "delete with success" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
