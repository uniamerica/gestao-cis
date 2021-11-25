const { Router } = require("express");
const { adminRoutes } = require("./adminRoutes");

const routes = Router();

routes.use("/admin", adminRoutes);

module.exports = {
  routes,
};
