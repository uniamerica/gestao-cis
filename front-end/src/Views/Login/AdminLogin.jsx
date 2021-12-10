import {
  Container,
  Box,
  Typography,
  TextField,
  Button,
  Tab,
  Tabs,
} from "@mui/material";
import React, { Fragment, useContext } from "react";
import PropTypes from "prop-types";
import Background from "../../assets/images/medicineBg.svg";
import { useForm } from "react-hook-form";
import { AuthContext } from "../../Contexts/authContext";
import { useNavigate } from "react-router";
import Footer from "../../Components/Footer";
import { api } from "../../services/api";
function TabPanel(props) {
  const { children, value, index, ...other } = props;
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

export default function Login() {
  const navigate = useNavigate();

  // AUTH CHECK
  const { addTokenInCookies, updateUserState } = useContext(AuthContext);
  const { register, handleSubmit } = useForm();

  // LOGIN COL
  const colSubmit = handleSubmit(async (data) => {
    try {
      const { email, password } = data;

      if (!email || !password) {
        throw new Error("Email ou Senha Inválida");
      }

      const response = await api.post("health-professionals/login", {
        email,
        password,
      });

      if (response.status !== 201) throw new Error("Email ou Senha Inválida");

      const { jwt } = response.data;

      addTokenInCookies(jwt);
      updateUserState(jwt);
      navigate("/home");
    } catch (error) {
      alert("Email ou Senha Inválida");
    }
  });

  // LOGIN ADM
  const adminSubmit = handleSubmit(async (data) => {
    try {
      const { username, password } = data;

      if (!username || !password) {
        throw new Error("Email ou Senha Inválida");
      }

      const response = await api.post("/admins/login", {
        username,
        password,
      });

      if (response.status !== 201) throw new Error("Email ou Senha Inválida");

      const { jwt } = response.data;

      addTokenInCookies(jwt);
      updateUserState(jwt);
      navigate("/home");
    } catch (error) {
      alert("Email ou Senha Inválida");
    }
  });

  // TABS
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Fragment>
      <Container
        maxWidth="lg"
        sx={{
          display: "flex",
          justifyContent: "center",
          paddingBottom: "3rem",
          boxShadown: "cccccc8f 8px 6px 4px",
        }}
      >
        <Box
          name="BackgroundImage"
          sx={{
            position: "fixed",
            zIndex: -1,
            maxHeight: "92vh",
            width: "100vw",
            display: "flex",
            justifyContent: "center",
          }}
        >
          <img src={Background} alt="" />
        </Box>
        <Box
          name="Card"
          sx={{
            width: 500,
            backgroundColor: "#ffff",
            marginTop: "12%",
            padding: "1rem",
            borderRadius: 1,
          }}
        >
          <Typography variant="h5" sx={{ textAlign: "center" }}>
            Centro Integrado de Saúde
          </Typography>
          <Box sx={{ display: "flex", justifyContent: "center" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
            >
              <Tab label="Colaborador" {...a11yProps(0)} />
              <Tab label="Administrador" {...a11yProps(1)} />
            </Tabs>
          </Box>
          <TabPanel value={value} index={0}>
            <Box
              component="form"
              onSubmit={colSubmit}
              sx={{
                display: "flex",
                flexDirection: "column",
                gap: "1rem",
                justifyContent: "center",
              }}
            >
              <Typography
                variant="h5"
                color="initial"
                sx={{ textAlign: "center" }}
              >
                Olá Colaborador! <br />
                Entre com seu email e senha!
              </Typography>
              <TextField
                required
                type="text"
                id="outlined-required"
                label="Email"
                {...register("email")}
              />
              <TextField
                required
                id="outlined-required"
                label="Senha"
                type="password"
                {...register("password")}
              />
              <Button type="submit" variant="contained">
                Login
              </Button>
            </Box>
          </TabPanel>
          <TabPanel value={value} index={1}>
            <Box
              component="form"
              onSubmit={adminSubmit}
              sx={{
                display: "flex",
                flexDirection: "column",
                gap: "1rem",
                justifyContent: "center",
              }}
            >
              <Typography
                variant="h5"
                color="initial"
                sx={{ textAlign: "center" }}
              >
                Olá Administrador! <br />
                Entre com seu email e senha!
              </Typography>
              <TextField
                required
                type="text"
                id="outlined-required"
                label="Email"
                {...register("email")}
              />
              <TextField
                required
                id="outlined-required"
                label="Senha"
                type="password"
                {...register("password")}
              />
              <Button type="submit" variant="contained">
                Login
              </Button>
            </Box>
          </TabPanel>
        </Box>
      </Container>
      <Footer />
    </Fragment>
  );
}
