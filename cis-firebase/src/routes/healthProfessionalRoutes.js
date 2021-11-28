const { Router } = require("express");
const healthProfessionalController = require("../controllers/healthProfessionalController");
const authMiddleware = require("../middlewares/authMiddleware");

const healthProfessionalRoutes = Router();

healthProfessionalRoutes.get("/", healthProfessionalController.index);
healthProfessionalRoutes.get(
  "/find",
  healthProfessionalController.findByEmailOrUsername
);
healthProfessionalRoutes.get("/:id", healthProfessionalController.findById);
healthProfessionalRoutes.post("/", healthProfessionalController.create);
healthProfessionalRoutes.post("/signin", healthProfessionalController.signIn);
healthProfessionalRoutes.put(
  "/:id",
  authMiddleware.compareIds,
  healthProfessionalController.update
);
healthProfessionalRoutes.delete(
  "/:id",
  authMiddleware.healthProfessional,
  authMiddleware.compareIds,
  healthProfessionalController.delete
);

module.exports = {
  healthProfessionalRoutes,
};
