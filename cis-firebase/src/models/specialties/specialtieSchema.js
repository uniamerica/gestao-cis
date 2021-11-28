const Joi = require("joi");

const specialtieSchema = Joi.object({
  id: Joi.string().guid({ version: ["uuidv4"] }),
  title: Joi.string().required(),
  description: Joi.string().max(255).required(),
});

module.exports = {
  specialtieSchema,
};
