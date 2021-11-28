const Joi = require("joi");

const appointmentSchema = Joi.object({
  id: Joi.string().guid({ version: ["uuidv4"] }),
  // date: Joi.date().greater("now"),
  date: Joi.string().required(),
  // date: Joi.date().timestamp("javascript"),
  time: Joi.string().required(),
  room: Joi.string().required(),
  observation: Joi.string().required(),
  patient: {
    dateOfBirth: Joi.string().required(),
    id: Joi.string().required(),
    name: Joi.string().required(),
    biologicalSex: Joi.string().max(1).required(),
    weight: Joi.number().required(),
    phone: Joi.string().required(),
  },
  healthProfessional: {
    email: Joi.string().email().required(),
    id: Joi.string().required(),
    name: Joi.string().required(),
    phone: Joi.string().required(),
    professionalDocument: Joi.string().required(),
  },
  price: Joi.number().required(),
  paid: Joi.boolean().required(),
});

module.exports = {
  appointmentSchema,
};
