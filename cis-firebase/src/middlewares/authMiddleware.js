const jwtUtils = require("../utils/jwtUtils");

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
      req.admin = true;
      req.tokenId = verify.data.id;
      next();
      return;
    } catch (error) {
      res.status(403).json({ error: error.message });
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

      const verify = jwtUtils.verifyJWT(token, "patient");

      if (!verify) {
        res.status(401).json({ error: "Invalid Token" });
        return;
      }
      req.tokenId = verify.data.id;
      next();
      return;
    } catch (error) {
      res.status(403).json({ error: error.message });
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
      req.tokenId = verify.data.id;
      next();
      return;
    } catch (error) {
      res.status(403).json({ error: error.message });
    }
  },
  adminOrPatient: async function (req, res, next) {
    try {
      const auth = req.headers.authorization;

      if (!auth) {
        res.status(401).json({ error: "Token is required" });
        return;
      }

      const token = auth.replace("Bearer ", "");

      const verifyAsAdmin = jwtUtils.verifyJWT(token, "admin");

      if (!!verifyAsAdmin) {
        req.admin = true;
        req.tokenId = verifyAsAdmin.data.id;
        next();
        return;
      }

      const verifyAsPatient = jwtUtils.verifyJWT(token, "patient");

      if (!!verifyAsPatient) {
        req.tokenId = verifyAsPatient.data.id;
        next();
        return;
      }
      res.status(401).json({ error: "Invalid Token" });
      return;
    } catch (error) {
      res.status(403).json({ error: error.message });
    }
  },
  adminOrHealthProfessional: async function (req, res, next) {
    try {
      const auth = req.headers.authorization;

      if (!auth) {
        res.status(401).json({ error: "Token is required" });
        return;
      }

      const token = auth.replace("Bearer ", "");

      const verifyAsAdmin = jwtUtils.verifyJWT(token, "admin");

      if (!!verifyAsAdmin) {
        req.tokenId = verifyAsAdmin.data.id;
        next();
        return;
      }

      const verifyAsHealthProfessional = jwtUtils.verifyJWT(
        token,
        "health_professional"
      );

      if (!!verifyAsHealthProfessional) {
        req.admin = true;
        req.tokenId = verifyAsHealthProfessional.data.id;
        next();
        return;
      }
      res.status(401).json({ error: "Invalid Token" });
      return;
    } catch (error) {
      res.status(403).json({ error: error.message });
    }
  },
  compareIds: function (req, res, next) {
    try {
      const { admin, tokenId } = req;
      const { id } = req.params;

      if (admin) {
        next();
        return;
      }

      if (tokenId !== id) {
        res.status(403).json({ error: "Invalid Token" });
        return;
      }

      next();
      return;
    } catch (error) {
      res.status(403).json({ error: "error.message" });
    }
  },
};
