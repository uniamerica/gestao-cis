const { Router } = require("express");
const patientController = require("../controllers/patientController");
const authMiddleware = require("../middlewares/authMiddleware");

const patientRoutes = Router();

patientRoutes.get("/patients", authMiddleware.patient, patientController.index);
patientRoutes.get(
  "/patients/find",
  authMiddleware.patient,
  patientController.findByEmailOrUsername
);
patientRoutes.get(
  "/patients/:id",
  authMiddleware.patient,
  patientController.findById
);
patientRoutes.post("/patients", patientController.create);
patientRoutes.post("/patients/signin", patientController.signIn);
patientRoutes.put(
  "/patients/:id",
  authMiddleware.patient,
  patientController.update
);
patientRoutes.delete(
  "/patients/:id",
  authMiddleware.patient,
  patientController.delete
);

module.exports = {
  patientRoutes,
};
