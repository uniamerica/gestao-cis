const { Router } = require("express");
const patientController = require("../controllers/patientController");
const authMiddleware = require("../middlewares/authMiddleware");

const patientRoutes = Router();

patientRoutes.get("/", authMiddleware.patient, patientController.index);
patientRoutes.get(
  "/find",
  authMiddleware.patient,
  patientController.findByEmailOrUsername
);
patientRoutes.get("/:id", authMiddleware.patient, patientController.findById);
patientRoutes.post("/", patientController.create);
patientRoutes.post("/signin", patientController.signIn);
patientRoutes.put("/:id", authMiddleware.patient, patientController.update);
patientRoutes.delete("/:id", authMiddleware.patient, patientController.delete);

module.exports = {
  patientRoutes,
};
