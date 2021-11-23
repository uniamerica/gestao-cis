const Joi = require("joi");

const healhProfessionalSchema = Joi.object({
  email: Joi.string().email().required(),
  name: Joi.string().required(),
  password: Joi.string().min(8).required(),
  phone: Joi.string().required(),
  professionalDocument: Joi.string().required(),
  specialty: Joi.string().required(),
});

module.exports = {
  healhProfessionalSchema,
};
