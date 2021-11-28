const { Router } = require("express");
const appointmentController = require("../controllers/appointmentController");
const authMiddleware = require("../middlewares/authMiddleware");

const appointmentRoutes = Router();

appointmentRoutes.get(
  "/",
  // authMiddleware.adminOrHealthProfessional,
  appointmentController.index
);
// // TO DO: VERIFY WHICH PARAMETERS COULD BE USED FOR THE SEARCH
// appointmentRoutes.get(
//   "/find",
//   authMiddleware.adminOrHealthProfessional,
//   appointmentController.findByName
// );
// appointmentRoutes.get(
//   "/:id",
//   authMiddleware.adminOrHealthProfessional,
//   appointmentController.findById
// );
// appointmentRoutes.post(
//   "/",
//   authMiddleware.adminOrHealthProfessional,
//   appointmentController.create
// );
// appointmentRoutes.put(
//   "/:id",
//   authMiddleware.adminOrHealthProfessional,
//   appointmentController.update
// );
// appointmentRoutes.delete(
//   "/:id",
//   authMiddleware.adminOrHealthProfessional,
//   appointmentController.delete
// );

module.exports = {
  appointmentRoutes,
};
