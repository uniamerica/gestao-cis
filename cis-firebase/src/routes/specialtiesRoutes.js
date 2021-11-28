const { Router } = require("express");
const specialtiesController = require("../controllers/specialtiesController");
const authMiddleware = require("../middlewares/authMiddleware");

const specialtiesRoutes = Router();

specialtiesRoutes.get(
  "/",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.index
);
specialtiesRoutes.get(
  "/find",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.findByTitle
);
specialtiesRoutes.get(
  "/:id",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.findById
);
specialtiesRoutes.post(
  "/",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.create
);
specialtiesRoutes.put(
  "/",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.update
);
specialtiesRoutes.delete(
  "/",
  authMiddleware.adminOrHealthProfessional,
  specialtiesController.delete
);

module.exports = {
  specialtiesRoutes,
};
