const { Router } = require("express");
const adminController = require("../controllers/adminController");
const authMiddleware = require("../middlewares/authMiddleware");

const adminRoutes = Router();

adminRoutes.get("/", authMiddleware.admin, adminController.index);
adminRoutes.get(
  "/find",
  authMiddleware.admin,
  adminController.findByEmailOrUsename
);
adminRoutes.get("/:id", authMiddleware.admin, adminController.findById);
adminRoutes.post("/", adminController.create);
adminRoutes.post("/signin", adminController.signIn);
adminRoutes.put("/:id", authMiddleware.admin, adminController.update);
adminRoutes.delete("/:id", authMiddleware.admin, adminController.delete);

module.exports = {
  adminRoutes,
};
