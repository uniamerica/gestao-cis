const { Router } = require("express");
const patientController = require("../controllers/patientController");
const authMiddleware = require("../middlewares/authMiddleware");

const patientRoutes = Router();

patientRoutes.get("/", patientController.index);
patientRoutes.get(
  "/find",

  patientController.findByEmailOrUsername
);
patientRoutes.get("/:id", patientController.findById);
patientRoutes.post("/", patientController.create);
patientRoutes.post("/signin", patientController.signIn);
patientRoutes.put(
  "/:id",
  authMiddleware.patient,
  authMiddleware.compareIds,
  patientController.update
);
patientRoutes.delete(
  "/:id",
  authMiddleware.patient,
  authMiddleware.compareIds,
  patientController.delete
);

module.exports = {
  patientRoutes,
};
