const Joi = require("joi");

const specialtieSchema = Joi.object({
  title: Joi.string().required(),
  description: Joi.string().max(255).required(),
});

module.exports = {
  specialtieSchema,
};
