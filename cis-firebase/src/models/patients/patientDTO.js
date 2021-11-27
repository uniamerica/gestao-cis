module.exports = (patient) => {
  delete patient.cpf;
  delete patient.rg;
  delete patient.mothername;
  delete patient.weight;
  delete patient.birthdate;
  delete patient.password;
  delete patient.address;

  return patient;
};
