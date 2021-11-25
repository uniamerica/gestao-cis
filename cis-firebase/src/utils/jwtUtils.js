const jwt = require("jsonwebtoken");
const { isDateBefore } = require("./dateUtils");
require("dotenv").config();

const ADMIN_SECRET = `${process.env.ADMIN_SECRET}`;
const HEALTH_PROFESSIONAL_SECRET = `${process.env.JWT_HEALTH_PROFESSIONAL_SECRET}`;
const PACIENT_SECRET = `${process.env.JWT_PACIENT_SECRET}`;

module.exports = {
  signJWT: function (obj, role) {
    let expDate = Math.floor(Date.now() / 1000) + 60 * 60 * 24; //86400s = 1 day;
    var secret;

    switch (role) {
      case "admin":
        secret = ADMIN_SECRET;
        break;
      case "health_professional":
        secret = HEALTH_PROFESSIONAL_SECRET;
        break;
      case "pacient":
        secret = PACIENT_SECRET;
        break;
      default:
        break;
    }

    const token = jwt.sign(
      {
        exp: expDate,
        data: obj,
      },
      secret
    );

    return token;
  },
  verifyJWT: function (token, role) {
    try {
      var secret;

      switch (role) {
        case "admin":
          secret = ADMIN_SECRET;
          break;
        case "health_professional":
          secret = HEALTH_PROFESSIONAL_SECRET;
          break;
        case "pacient":
          secret = PACIENT_SECRET;
          break;
        default:
          break;
      }

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
  },
};
