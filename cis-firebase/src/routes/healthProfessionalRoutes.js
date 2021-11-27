const { Router } = require("express");
const professionalController = require("../controllers/professionalController");
const authMiddleware = require("../middlewares/authMiddleware");

const professionalRoutes = Router();

professionalRoutes.get(
  "/",
  authMiddleware.healthProfessional,
  professionalController.index
);
professionalRoutes.get(
  "/find",
  authMiddleware.healthProfessional,
  professionalController.findByEmailOrUsername
);
professionalRoutes.get(
  "/:id",
  authMiddleware.healthProfessional,
  professionalController.findById
);
professionalRoutes.post("/", professionalController.create);
professionalRoutes.post("/signin", professionalController.signIn);
professionalRoutes.put(
  "/:id",
  authMiddleware.healthProfessional,
  professionalController.update
);
professionalRoutes.delete(
  "/:id",
  authMiddleware.healthProfessional,
  professionalController.delete
);

module.exports = {
  professionalRoutes,
};
