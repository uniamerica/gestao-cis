const jwt = require("jsonwebtoken");
const { addOneDay, isDateBefore } = require("./dateUtils");
require("dotenv").config();

const secret = process.env.JWT_SECRET;

const signJWT = (obj) => {
  let expDate = addOneDay(Date.now());

  const token = jwt.sign({ exp: expDate, data: obj }, secret);

  return token;
};

const verifyJWT = (token) => {
  try {
    const decoded = jwt.verify(token, secret);
    const createdAt = new Date(decoded.exp);

    const verifyDate = isDateBefore(createdAt, Date.now());

    if (verifyDate) {
      return decoded;
    } else {
      return false;
    }
  } catch (error) {
    return false;
  }
};

module.exports = {
  signJWT,
  verifyJWT,
};
