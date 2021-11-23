const bcrypt = require("bcrypt");

const salt = 8;

const encryptPassword = async (password) => await bcrypt.hash(password, salt);

const comparePassword = async (password, hash) =>
  await bcrypt.compare(password, hash);

module.exports = {
  encryptPassword,
  comparePassword,
};
