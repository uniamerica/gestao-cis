const { Router } = require("express");
const healthProfessionalController = require("../controllers/healthProfessionalController");
const authMiddleware = require("../middlewares/authMiddleware");

const healthProfessionalRoutes = Router();

healthProfessionalRoutes.get(
  "/",
  authMiddleware.healthProfessional,
  healthProfessionalController.index
);
healthProfessionalRoutes.get(
  "/find",
  authMiddleware.healthProfessional,
  healthProfessionalController.findByEmailOrUsername
);
healthProfessionalRoutes.get(
  "/:id",
  authMiddleware.healthProfessional,
  healthProfessionalController.findById
);
healthProfessionalRoutes.post("/", healthProfessionalController.create);
healthProfessionalRoutes.post("/signin", healthProfessionalController.signIn);
healthProfessionalRoutes.put(
  "/:id",
  authMiddleware.healthProfessional,
  healthProfessionalController.update
);
healthProfessionalRoutes.delete(
  "/:id",
  authMiddleware.healthProfessional,
  healthProfessionalController.delete
);

module.exports = {
  healthProfessionalRoutes,
};
