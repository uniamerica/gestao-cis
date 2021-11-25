const { Router } = require("express");
const { verifyJWT } = require("../utils/jwtUtils");
const { adminRoutes } = require("./adminRoutes");

const routes = Router();

routes.use("/admin", adminRoutes);
routes.get("/test", (req, res) => {
  try {
    const { token } = req.body;
    const isValidToken = verifyJWT(token);

    res.json({ isValidToken }).status(200);
  } catch (error) {
    res.json(error).status(400);
  }
});

module.exports = {
  routes,
};
