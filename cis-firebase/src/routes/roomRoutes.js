const { Router } = require("express");
const roomController = require("../controllers/roomController");
const authMiddleware = require("../middlewares/authMiddleware");

const roomRoutes = Router();

roomRoutes.get("/", authMiddleware.healthProfessional, roomController.index);
roomRoutes.get(
  "/find",
  authMiddleware.healthProfessional,
  roomController.findByName
);
roomRoutes.get(
  "/:id",
  authMiddleware.healthProfessional,
  roomController.findById
);
roomRoutes.post("/", authMiddleware.healthProfessional, roomController.create);
roomRoutes.put(
  "/:id",
  authMiddleware.healthProfessional,
  roomController.update
);
roomRoutes.delete(
  "/:id",
  authMiddleware.healthProfessional,
  roomController.delete
);

module.exports = {
  roomRoutes,
};
