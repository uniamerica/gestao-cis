const { Router } = require("express");
const { adminRoutes } = require("./adminRoutes");
const { patientRoutes } = require("./patientRoutes");
const { roomRoutes } = require("./roomRoutes");

const routes = Router();

routes.use("/admin", adminRoutes);
routes.use("/patients", patientRoutes);
routes.use("/rooms", roomRoutes);

module.exports = {
  routes,
};
