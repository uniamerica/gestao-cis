const Joi = require("joi");

const roomSchema = Joi.object({
  name: Joi.string().required(),
  specialties: Joi.array().items(Joi.string().required()).required(),
});

module.exports = {
  roomSchema,
};
