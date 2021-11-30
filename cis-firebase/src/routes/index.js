const { Router } = require("express");
const { adminRoutes } = require("./adminRoutes");
const { patientRoutes } = require("./patientRoutes");
const { roomRoutes } = require("./roomRoutes");

const swaggerUi = require("swagger-ui-express");
const doc_v1 = require("../swagger/v1.json");

const routes = Router();

routes.use("/admin", adminRoutes);
routes.use("/patients", patientRoutes);
routes.use("/rooms", roomRoutes);

routes.use("/api-docs/v1", swaggerUi.serve, swaggerUi.setup(doc_v1));

module.exports = {
  routes,
};
