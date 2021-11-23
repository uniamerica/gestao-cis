const express = require("express");
const cors = require("cors");
const { routes } = require("./routes");

const app = express();

// CONFIGURAR SERVER PARA UTILIZAR JSON
app.use(express.json());

// CONFIGURAR CORS
app.use(cors());

// ROTAS ENDPOINTS
app.use(routes);

module.exports = {
  app,
};
