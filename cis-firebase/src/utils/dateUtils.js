const { format, isBefore, addDays } = require("date-fns");

const formatDate = (date) => format(date, "dd-MM-yyy");

const isDateBefore = (date, dateToCompare) => isBefore(date, dateToCompare);

const addOneDay = (date) => addDays(date, 1);

module.exports = {
  formatDate,
  isDateBefore,
  addOneDay,
};
