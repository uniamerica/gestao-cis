const appointmentService = require("../services/appointmentService");

module.exports = {
  // LIST ALL PAGINATED
  index: async (req, res) => {
    try {
      const { startAfter, limit, order, desc } = req.query;
      const data = await appointmentService.index(
        startAfter,
        limit,
        order,
        desc
      );

      res.json(data).status(200);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  },
};
