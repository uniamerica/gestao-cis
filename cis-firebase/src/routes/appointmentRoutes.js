const { Router } = require("express");
const appointmentController = require("../controllers/appointmentController");
const authMiddleware = require("../middlewares/authMiddleware");

const appointmentRoutes = Router();

appointmentRoutes.get(
  "/",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.index
);
appointmentRoutes.get(
  "/find",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.findByBooking
);
appointmentRoutes.get(
  "/:id",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.findById
);
appointmentRoutes.post(
  "/",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.create
);
appointmentRoutes.put(
  "/:id",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.update
);
appointmentRoutes.delete(
  "/:id",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.delete
);

module.exports = {
  appointmentRoutes,
};
