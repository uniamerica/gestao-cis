const roomService = require("../services/roomService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await roomService.index(startAfter, limit, order, desc);

      res.status(200).json(data);
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
        res.status(200).json(dataByName);
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

      res.status(200).json(data);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // CREATE
  create: async (req, res) => {
    try {
      const data = await roomService.create(req.body);
      if (!!data.error) {
        throw new Error(error.message);
      }

      res.status(201).json({ success: "Created with success!" });
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

      res.status(200).json({ success: "Record successfully updated!" });
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

      res.status(200).json({ success: "Successfully deleted!" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
