const jwt = require("jsonwebtoken");
const fns = require("date-fns");
const { verifyJWT } = require("../utils/jwtUtils");
require("dotenv").config();

const authMiddleware = async (req, res, next) => {
  try {
    const auth = req.headers.authorization;

    if (!auth) {
      res.status(401).json({ error: "Token is required" });
      return;
    }

    const token = auth.replace("Bearer ", "");

    const verify = verifyJWT(token);
    if (!verify) {
      res.status(401).json({ error: "Invalid Token" });
      return;
    }

    next();
    return;
  } catch (error) {
    throw new Error(error);
  }
};

module.exports = {
  authMiddleware,
};
