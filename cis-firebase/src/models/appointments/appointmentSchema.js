const Joi = require("joi");

const appointmentSchema = Joi.object({
  id: Joi.string().guid({ version: ["uuidv4"] }),
  date: Joi.string().required(),
  time: Joi.string().required(),
  room: Joi.string().required(),
  observation: Joi.string().required(),
  patient: Joi.string().required(),
  healthProfessional: Joi.string().required(),
  price: Joi.number().required(),
  paid: Joi.boolean().required(),
});

module.exports = {
  appointmentSchema,
};
