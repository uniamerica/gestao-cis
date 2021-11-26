const { Router } = require("express");
const roomController = require("../controllers/roomController");
const authMiddleware = require("../middlewares/authMiddleware");

const roomRoutes = Router();

roomRoutes.get("/", authMiddleware.healthProfessional, roomController.index);

module.exports = {
  roomRoutes,
};
