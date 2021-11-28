const { Router } = require("express");
const specialtiesController = require("../controllers/specialtiesController");
const authMiddleware = require("../middlewares/authMiddleware");

const specialtiesRoutes = Router();

specialtiesRoutes.get("/", specialtiesController.index);

module.exports = {
  specialtiesRoutes,
};
