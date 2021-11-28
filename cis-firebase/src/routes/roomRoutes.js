const { Router } = require("express");
const roomController = require("../controllers/roomController");
const authMiddleware = require("../middlewares/authMiddleware");

const roomRoutes = Router();

roomRoutes.get(
  "/",
  authMiddleware.adminOrHealthProfessional,
  roomController.index
);
roomRoutes.get(
  "/find",
  authMiddleware.adminOrHealthProfessional,
  roomController.findByName
);
roomRoutes.get(
  "/:id",
  authMiddleware.adminOrHealthProfessional,
  roomController.findById
);
roomRoutes.post(
  "/",
  authMiddleware.adminOrHealthProfessional,
  roomController.create
);
roomRoutes.put(
  "/:id",
  authMiddleware.adminOrHealthProfessional,
  roomController.update
);
roomRoutes.delete(
  "/:id",
  authMiddleware.adminOrHealthProfessional,
  roomController.delete
);

module.exports = {
  roomRoutes,
};
