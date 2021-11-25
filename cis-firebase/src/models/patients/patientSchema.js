const Joi = require("joi");

const patientSchema = Joi.object({
  id: Joi.string()
    .guid({ version: ["uuidv4"] })
    .required(),
  cpf: Joi.string().required(),
  rg: Joi.string().required(),
  name: Joi.string().required(),
  email: Joi.string().email().required(),
  mothername: Joi.string().required(),
  phone: Joi.string().required(),
  weight: Joi.number().required(),
  birthdate: Joi.string().required(),
  biologicalSex: Joi.string().max(1).required(),
  address: {
    cep: Joi.string().required(),
    city: Joi.string().required(),
    country: Joi.string().required(),
    neigborhood: Joi.string().required(),
    number: Joi.string().required(),
    street: Joi.string().required(),
    uf: Joi.string().required(),
  },
});

module.exports = {
  patientSchema,
};
