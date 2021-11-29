const specialtiesService = require("../services/specialtiesService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await specialtiesService.index(
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

  // FIND BY TITLE
  findByTitle: async (req, res) => {
    try {
      const { title } = req.query;

      if (!title) {
        throw new Error("Title not specified");
      }

      const dataByTitle = await specialtiesService.findByTitle(title);

      if (dataByTitle) {
        res.status(200).json(dataByTitle);
      }
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },

  // FIND BY ID
  findById: async (req, res) => {
    try {
      const { id } = req.params;
      const data = await specialtiesService.findById(id);

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
      const data = await specialtiesService.create(req.body);
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
      const data = await specialtiesService.update(id, req.body);
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
      const data = await specialtiesService.delete(id);
      if (!!data.error) {
        throw new Error(error.message);
      }

      res.status(200).json({ success: "Successfully deleted!" });
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
