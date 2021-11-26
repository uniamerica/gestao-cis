const jwt = require("jsonwebtoken");
const fns = require("date-fns");
const jwtUtils = require("../utils/jwtUtils");
require("dotenv").config();

module.exports = {
  admin: async function (req, res, next) {
    try {
      const auth = req.headers.authorization;

      if (!auth) {
        res.status(401).json({ error: "Token is required" });
        return;
      }

      const token = auth.replace("Bearer ", "");

      const verify = jwtUtils.verifyJWT(token, "admin");

      if (!verify) {
        res.status(401).json({ error: "Invalid Token" });
        return;
      }

      next();
      return;
    } catch (error) {
      throw new Error(error);
    }
  },

  patient: async function (req, res, next) {
    try {
      const auth = req.headers.authorization;

      if (!auth) {
        res.status(401).json({ error: "Token is required" });
        return;
      }

      const token = auth.replace("Bearer ", "");

      const verify = jwtUtils.verifyJWT(token, "pacient");

      if (!verify) {
        res.status(401).json({ error: "Invalid Token" });
        return;
      }

      next();
      return;
    } catch (error) {
      throw new Error(error);
    }
  },

  healthProfessional: async function (req, res, next) {
    try {
      const auth = req.headers.authorization;

      if (!auth) {
        res.status(401).json({ error: "Token is required" });
        return;
      }

      const token = auth.replace("Bearer ", "");

      const verify = jwtUtils.verifyJWT(token, "health_professional");

      if (!verify) {
        res.status(401).json({ error: "Invalid Token" });
        return;
      }

      next();
      return;
    } catch (error) {
      throw new Error(error);
    }
  },
};
