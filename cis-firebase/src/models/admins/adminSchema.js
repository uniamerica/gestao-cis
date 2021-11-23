const Joi = require("joi");

const adminSchema = Joi.object({
  name: Joi.string().required(),
  email: Joi.string().email().required(),
  password: Joi.string().min(8).required(),
  phone: Joi.string().required(),
  username: Joi.string().required(),
});

module.exports = {
  adminSchema,
};
