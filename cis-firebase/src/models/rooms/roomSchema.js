const Joi = require("joi");

const roomSchema = Joi.object({
  id: Joi.string()
    .guid({ version: ["uuidv4"] })
    .required(),
  name: Joi.string().required(),
  specialties: Joi.array().items(Joi.string().required()).required(),
});

module.exports = {
  roomSchema,
};
