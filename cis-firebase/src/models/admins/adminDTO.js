module.exports = (admin) => {
  delete admin.password;

  return admin;
};
