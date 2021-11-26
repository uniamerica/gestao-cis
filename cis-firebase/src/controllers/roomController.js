const roomService = require("../services/roomService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await roomService.index(startAfter, limit, order, desc);

      res.json(data).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY NAME
  findByName: async (req, res) => {
    try {
      const { name } = req.query;

      if (!name) {
        throw new Error("Name not specified");
      }

      const dataByName = await roomService.findByName(name);

      if (dataByName) {
        res.json(dataByName).status(200);
      }
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY ID
  findById: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await roomService.findById(id);

      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json(data).status(200);
    } catch (error) {
      throw new Error(error.message);
    }
  },

  // CREATE
  create: async (req, res) => {
    try {
      const data = await roomService.create(req.body);
      if (!!data.error) {
        throw new Error(error.message);
      }

      res.json({ success: "Created with success!" }).status(201);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // UPDATE
  update: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await roomService.update(id, req.body);
      if (!!data.error) {
        throw new Error(error.message);
      }

      res.json({ success: "Record successfully updated!" }).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // DELETE
  delete: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await roomService.delete(id);
      if (!!data.error) {
        throw new Error(data.error);
      }

      res.json({ success: "Successfully deleted!" }).status(200);
    } catch (error) {
      res.json({ error: error.message });
    }
  },
};
