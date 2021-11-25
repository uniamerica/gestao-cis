const { Router } = require("express");
const adminController = require("../controllers/adminController");
const { authMiddleware } = require("../middlewares/authMiddleware");

const adminRoutes = Router();

adminRoutes.get("/", authMiddleware, adminController.index);
adminRoutes.get("/find", authMiddleware, adminController.findByEmailOrUsename);
adminRoutes.get("/:id", authMiddleware, adminController.findById);
adminRoutes.post("/", adminController.create);
adminRoutes.post("/signin", adminController.signIn);
adminRoutes.put("/:id", authMiddleware, adminController.update);
adminRoutes.delete("/:id", authMiddleware, adminRoutes.delete);

module.exports = {
  adminRoutes,
};
