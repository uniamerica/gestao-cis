const { Router } = require("express");
const { adminRoutes } = require("./adminRoutes");
const { patientRoutes } = require("./patientRoutes");

const routes = Router();

routes.use("/admin", adminRoutes);
routes.use("/patients", patientRoutes);

module.exports = {
  routes,
};
