import { Container, Box, Typography, TextField, Button, Tab, Tabs } from "@mui/material";
import React, { Fragment, useContext, useEffect } from "react";
import PropTypes from "prop-types";
import Background from "../../assets/images/medicineBg.svg";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { useForm } from "react-hook-form";
import axios from "axios";
import { AuthContext } from "../../Contexts/authContext";
import { useNavigate } from "react-router";
import Cookies from "js-cookie";
import { Link } from 'react-router-dom'

// PAINEIS START -----------------
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

// PAINEIS END ---------------------------------------------

export default function Login() {
  const navigate = useNavigate();
  const { isAuth, setIsAuth } = useContext(AuthContext);

  // PAINEIS -----------------------------------------------
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  // PAINEIS END ---------------------------------------------

  // useEffect(() => {
  //   if (isAuth) {
  //     return navigate("/home");
  //   } else {
  //     return console.log("Credenciais Inválidas");
  //   }
  // }, [isAuth, navigate]);

  // LOGIN ---------------------------------------------------
  const { register, handleSubmit } = useForm();
  const onSubmit = handleSubmit(async (data) => {
    const response = await axios.post(
      "http://localhost:8080/admin/login",
      data
    );
    if (response.status === 200) {
      const { token } = response.data;
      Cookies.set("cis.validator", token, {
        expires: 1, //Expira em 1 dia
      });
      // Cookies.remove('cis.validator')
      setIsAuth(true);
      navigate("/home");
    } else {
      alert("Credenciais Inválidas");
    }
  });

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
            width: 600,
            backgroundColor: "#ffff",
            marginTop: "10%",
            padding: "1rem",
            borderRadius: "8px",
          }}
        >
          <Typography variant="h5" sx={{ textAlign: "center" }}>
            Centro Integrado de Saúde
          </Typography>
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
            }}
          >
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
            >
              <Tab label="Administrador" {...a11yProps(0)} />
              <Tab label="Colaborador" {...a11yProps(1)} />
            </Tabs>
          </Box>
          <TabPanel value={value} index={0}>
          <div
              name="FormLogin"
              style={{
                width: "100%",
                height: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <Box
                component="form"
                data-testid="form_test"
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  width: "80%",
                  justifyContent: "center",
                }}
                onSubmit={onSubmit}
              >
                {/* <AccountCircleIcon
                  sx={{ alignSelf: "center", fontSize: "60px" }}
                /> */}
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
                  label="Email ou Username"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("username")}
                />
                <TextField
                  required
                  id="outlined-required"
                  label="Senha"
                  sx={{ marginTop: "1.5rem" }}
                  type="password"
                  {...register("password")}
                />
                <Button
                  type="submit"
                  variant="contained"
                  sx={{ marginTop: "2rem" }}
                >
                  Login
                </Button>
              </Box>
            </div>
          </TabPanel>

          <TabPanel value={value} index={1}>
            <div
              name="FormCadastro"
              style={{
                width: "100%",
                height: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <Box
                component="form"
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  width: "80%",
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
                  label="Email ou Username"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("username")}
                />
                <TextField
                  required
                  id="outlined-required"
                  label="Senha"
                  sx={{ marginTop: "1.5rem" }}
                  type="password"
                  {...register("password")}
                />
                <Button
                  type="submit"
                  variant="contained"
                  sx={{ marginTop: "2rem" }}
                >
                  Login
                </Button>
              </Box>
            </div>
          </TabPanel>
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
            }}
          >
            <div
              name="FormLogin"
              style={{
                width: "100%",
                height: "100%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <Box
                component="form"
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  width: "80%",
                  justifyContent: "center",
                }}
                onSubmit={onSubmit}
              >
                {/* <AccountCircleIcon
                  sx={{ alignSelf: "center", fontSize: "60px" }}
                /> */}
                
              </Box>
            </div>
          </Box>
        </Box>
      </Container>
    </Fragment>
  );
}
