import {
  TextField,
  Button,
  Container,
  Box,
  Typography,
  Tab,
  Tabs,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
} from "@mui/material";
import React, { Fragment, useContext } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import Background from "../../assets/images/medicineBg.svg";
import { useForm } from "react-hook-form";
import { AuthContext } from "../../Contexts/authContext";
import { useNavigate } from "react-router";
import { api } from "../../services/api";

// PAINEIS
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
  const { register, handleSubmit } = useForm();
  const { addTokenInCookies, updateUserState } = useContext(AuthContext);

  const handlePatientLogin = handleSubmit(async (data) => {
    try {
      const { email, password } = data;

      if (!email || !password) {
        throw new Error("Email ou Senha Inválida");
      }

      const response = await api.post("/patients/login", { email, password });

      if (response.status !== 201) throw new Error("Email ou Senha Inválida");

      const { jwt } = response.data;

      addTokenInCookies(jwt);
      updateUserState(jwt);
      navigate("/home");
    } catch (error) {
      alert("Email ou Senha Inválida");
    }
  });

  const handlePatientRegister = handleSubmit(async (data) => {
    try {
      const responseRegister = await api.post("/patients", data);
      if (responseRegister.status !== 201)
        throw new Error("Algo de errado aconteceu!");

      const responseLogin = await api.post("/patients/login", {
        email: data.email,
        password: data.password,
      });

      if (responseLogin.status !== 201)
        throw new Error("Email ou Senha Inválida");

      const { jwt } = responseLogin.data;

      addTokenInCookies(jwt);
      updateUserState(jwt);
      navigate("/home");
    } catch (error) {
      alert(error.message);
    }
  });

  // PAINEIS
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
          <Typography
            variant="h5"
            sx={{ textAlign: "center" }}
            data-testid="login-title"
          >
            Centro Integrado de Saúde
          </Typography>
          <Box sx={{ display: "flex", justifyContent: "center" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
            >
              <Tab label="Login" {...a11yProps(0)} data-testid="test-tab0" />
              <Tab
                label="Cadastrar"
                {...a11yProps(1)}
                data-testid="test-tab1"
              />
            </Tabs>
          </Box>
          <TabPanel value={value} index={0}>
            <Box
              component="form"
              data-testid="test-form"
              onSubmit={handlePatientLogin}
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
                Já é nosso paciente? <br />
                Entre com seu email e senha!
              </Typography>
              <TextField
                required
                type="text"
                id="outlined-required"
                label="Email"
                {...register("email")}
                inputProps={{ "data-testid": "input-email" }}
              />
              <TextField
                required
                id="outlined-required"
                label="Senha"
                type="password"
                {...register("password")}
                inputProps={{ "data-testid": "input-password" }}
              />
              <Button
                type="submit"
                variant="contained"
                data-testid="input-button"
              >
                Login
              </Button>
              <Typography
                variant="p"
                color="initial"
                sx={{ margin: "auto", marginTop: "1.5rem" }}
              >
                Colaborador? <Link to="/admin/login">Clique aqui</Link> <br />
              </Typography>
            </Box>
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
                onSubmit={handlePatientRegister}
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
                  Olá! Estamos muito felizes em ter você por aqui
                </Typography>
                <Typography
                  variant="h6"
                  color="initial"
                  sx={{ marginTop: "32px" }}
                >
                  Para realizar seu cadastro, preencha os campos abaixo:
                </Typography>
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Nome Completo"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("name")}
                />
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                  }}
                >
                  <TextField
                    required
                    type="date"
                    id="outlined-required"
                    label="Data de Nascimento"
                    sx={{ marginTop: "1.5rem" }}
                    {...register("dateOfBirth")}
                  />
                  <FormControl
                    component="fieldset"
                    sx={{ marginTop: "1.5rem" }}
                  >
                    <FormLabel component="legend">Gênero</FormLabel>
                    <RadioGroup {...register("gender")}>
                      <FormControlLabel
                        value="M"
                        control={<Radio />}
                        label="Masculino"
                      />
                      <FormControlLabel
                        value="F"
                        control={<Radio />}
                        label="Feminino"
                      />
                    </RadioGroup>
                  </FormControl>
                </div>
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="CPF"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("cpf")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="RG"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("rg")}
                />
                <TextField
                  required
                  type="email"
                  id="outlined-required"
                  label="Email"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("email")}
                />
                <TextField
                  required
                  type="password"
                  id="outlined-required"
                  label="Senha"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("password")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Nome da Mãe"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("motherName")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Telefone"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("phone")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Rua"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("street")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Cidade"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("city")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Bairro"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("neighborhood")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="País"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("addressLine2")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="UF"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("UF")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="Número"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("addressNumber")}
                />
                <TextField
                  required
                  type="text"
                  id="outlined-required"
                  label="CEP"
                  sx={{ marginTop: "1.5rem" }}
                  {...register("cep")}
                />
                <Button
                  variant="contained"
                  type="submit"
                  sx={{ marginTop: "2rem" }}
                >
                  Cadastrar
                </Button>
              </Box>
            </div>
          </TabPanel>
        </Box>
      </Container>
    </Fragment>
  );
}
