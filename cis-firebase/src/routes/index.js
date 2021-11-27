const { Router } = require("express");
const { adminRoutes } = require("./adminRoutes");
const { patientRoutes } = require("./patientRoutes");
const { roomRoutes } = require("./roomRoutes");
const { healthProfessionalRoutes } = require("./healthProfessionalRoutes");

const routes = Router();

routes.use("/admin", adminRoutes);
routes.use("/patients", patientRoutes);
routes.use("/rooms", roomRoutes);
routes.use("/professionals", healthProfessionalRoutes);

module.exports = {
  routes,
};
