const jwt = require("jsonwebtoken");
const { isDateBefore } = require("./dateUtils");
require("dotenv").config();

const secret = `${process.env.SECRET}`;

const signJWT = (obj) => {
  let expDate = Math.floor(Date.now() / 1000) + 60 * 60 * 24; //86400s = 1 day;

  const token = jwt.sign(
    {
      exp: expDate,
      data: obj,
    },
    secret
  );

  return token;
};

const verifyJWT = (token) => {
  try {
    const decoded = jwt.verify(token, secret);
    const expiredDate = new Date(decoded.exp);

    const verifyDate = isDateBefore(expiredDate, Date.now());

    if (verifyDate) {
      return decoded;
    } else {
      return false;
    }
  } catch (error) {
    throw new Error(error);
  }
};

module.exports = {
  signJWT,
  verifyJWT,
};
