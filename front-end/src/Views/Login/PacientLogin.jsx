import { TextField, Button, Container, Box, Typography, Tab, Tabs, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, } from "@mui/material";
import React, { Fragment, useContext, useEffect } from "react";
import { Link } from 'react-router-dom';
import PropTypes from "prop-types";
import Background from "../../assets/images/medicineBg.svg";
import { useForm } from "react-hook-form";
import { AuthContext } from "../../Contexts/authContext";
import { useNavigate } from "react-router";
import axios from "axios";
import Cookies from "js-cookie";
import jsonwebtoken from 'jsonwebtoken';

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
  const { isAuth, setIsAuth, setUser } = useContext(AuthContext);

  const loginSubmit = handleSubmit(async (data) => {
    const response = await axios.post("http://localhost:8080/api/patients/login", data);
    if (response.status === 201) {
      const { jwt } = response.data;
      setUser(jsonwebtoken.decode(jwt));
      Cookies.set("cis.validator", jwt, {expires: 1,});
      setIsAuth(true);
      navigate("/home");
    } else {
      alert("Credenciais Inválidas");
    }
  });

  // PRECISA TRABALHAR NISSO
  const singupSubmit = handleSubmit(async (data) => {
    const response = await axios.post("http://localhost:8080/api/patients/login", data);
    if (response.status === 201) {
      const { jwt } = response.data;
      setUser(jsonwebtoken.decode(jwt));
      Cookies.set("cis.validator", jwt, {expires: 1,});
      setIsAuth(true);
      navigate("/home");
    } else {
      alert("Credenciais Inválidas");
    }
  });
  
  // PAINEIS
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };


  // LOGIN
  const onSubmit = handleSubmit(async (data) => {
    window.alert("Hello World");
  });

  return (
    <Fragment>
      <Container maxWidth="lg" sx={{display: "flex", justifyContent: "center", paddingBottom: "3rem", boxShadown: "cccccc8f 8px 6px 4px"}}>
        <Box name="BackgroundImage" sx={{ position: "fixed", zIndex: -1, maxHeight: "92vh", width: "100vw", display: "flex", justifyContent: "center", }}>
          <img src={Background} alt="" />
        </Box>
        <Box name="Card" sx={{ width: 500, backgroundColor: "#ffff", marginTop: "12%", padding: "1rem", borderRadius: 1, }}>
          <Typography variant="h5" sx={{ textAlign: "center" }}>
            Centro Integrado de Saúde
          </Typography>
          <Box sx={{ display: "flex", justifyContent: "center"}}>
            <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
              <Tab label="Login" {...a11yProps(0)} />
              <Tab label="Cadastrar" {...a11yProps(1)} />
            </Tabs>
          </Box>
          <TabPanel value={value} index={0}>
              <Box component="form" onSubmit={loginSubmit} sx={{ display: "flex", flexDirection: "column", gap: '1rem', justifyContent: "center", }}>
                <Typography variant="h5" color="initial" sx={{ textAlign: "center" }}>
                  Já é nosso paciente? <br />
                  Entre com seu email e senha!
                </Typography>
                <TextField required type="text" id="outlined-required" label="Email" {...register("email")}/>
                <TextField required id="outlined-required" label="Senha" type="password" {...register("password")}/>
                <Button type="submit" variant="contained">
                  Login
                </Button>
                <Typography variant="p" color="initial" sx={{margin: 'auto', marginTop: '1.5rem'}}>
                  Colaborador? <Link to='/admin/login'>Clique aqui</Link> <br />
                </Typography>
              </Box>
          </TabPanel>
          <TabPanel value={value} index={1}>
          <div 
              name="FormCadastro"
              style={{ width: "100%", height: "100%", display: "flex", justifyContent: "center", alignItems: "center", }}>
              <Box component="form" sx={{ display: "flex", flexDirection: "column", width: "80%", justifyContent: "center", }}>
                <Typography variant="h5" color="initial" sx={{ textAlign: "center" }}>
                  Olá! Estamos muito felizes em ter você por aqui
                </Typography>
                <Typography variant="h6" color="initial" sx={{ marginTop: "32px" }}>
                  Para realizar seu cadastro, preencha os campos abaixo:
                </Typography>
                <TextField required type="text" id="outlined-required" label="Nome Completo" sx={{ marginTop: "1.5rem" }}/>
                <div style={{  display: "flex", alignItems: "center", justifyContent: "space-between", }}>
                  <TextField required type="date" id="outlined-required" label="Data de Nascimento" value={"2000-01-01"} sx={{ marginTop: "1.5rem" }}/>
                  <FormControl component="fieldset"  sx={{ marginTop: "1.5rem" }}>
                    <FormLabel component="legend">Gênero</FormLabel>
                    <RadioGroup>
                      <FormControlLabel value="M" control={<Radio />} label="Masculino" />
                      <FormControlLabel value="F" control={<Radio />} label="Feminino" />
                    </RadioGroup>
                  </FormControl>
                </div>
                <TextField required type="text" id="outlined-required" label="CPF" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="RG" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="email" id="outlined-required" label="Email" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="Telefone" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="Rua" sx={{ marginTop: "1.5rem" }} />
                <TextField required type="text" id="outlined-required" label="Cidade" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="Bairro" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="País" sx={{ marginTop: "1.5rem" }} />
                <TextField required type="text" id="outlined-required" label="UF" sx={{ marginTop: "1.5rem" }} />
                <TextField required type="text" id="outlined-required" label="Número" sx={{ marginTop: "1.5rem" }}/>
                <TextField required type="text" id="outlined-required" label="CEP" sx={{ marginTop: "1.5rem" }} />
                <Button variant="contained" sx={{ marginTop: "2rem" }}>
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
