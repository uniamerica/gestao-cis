const Joi = require("joi");

const healthProfessionalSchema = Joi.object({
  id: Joi.string()
    .guid({ version: ["uuidv4"] })
    .required(),
  email: Joi.string().email().required(),
  name: Joi.string().required(),
  username: Joi.string().required(),
  password: Joi.string().min(8).required(),
  phone: Joi.string().required(),
  professionalDocument: Joi.string().required(),
  specialty: Joi.string().required(),
});

module.exports = {
  healthProfessionalSchema,
};
