const Joi = require("joi");

const diagnosesSchema = Joi.object({
  id: Joi.string()
    .guid({ version: ["uuidv4"] })
    .required(),
  appointment: {
    date: Joi.string().required(),
    observation: Joi.string().required(),
    time: Joi.string().required(),
  },
  healthProfessional: {
    name: Joi.string().required(),
    professionalDocument: Joi.string().required(),
  },
  patient: {
    biologicalSex: Joi.string().max(1).required(),
    birthdate: Joi.string().required(),
    name: Joi.string().required(),
    weight: Joi.number().required(),
  },
  observation: Joi.string().required(),
  prescription: Joi.string().required(),
});

module.exports = {
  diagnosesSchema,
};
