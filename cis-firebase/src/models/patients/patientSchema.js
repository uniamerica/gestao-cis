const Joi = require("joi");

const patientSchema = Joi.object({
  id: Joi.string().guid({ version: ["uuidv4"] }),
  cpf: Joi.string().required(),
  rg: Joi.string().required(),
  name: Joi.string().required(),
  email: Joi.string().email().required(),
  username: Joi.string().required(),
  motherName: Joi.string().required(),
  phone: Joi.string().required(),
  weight: Joi.number().required(),
  birthdate: Joi.string().required(),
  biologicalSex: Joi.string().max(1).required(),
  password: Joi.string().min(8).required(),
  address: {
    cep: Joi.string().required(),
    city: Joi.string().required(),
    country: Joi.string().required(),
    neighborhood: Joi.string().required(),
    number: Joi.string().required(),
    street: Joi.string().required(),
    uf: Joi.string().required(),
  },
});

module.exports = {
  patientSchema,
};
