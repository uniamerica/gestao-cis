import {
  Container,
  Box,
  Typography,
  TextField,
  Button
} from "@mui/material";
import React, { Fragment, useContext, useEffect } from "react";
import Background from "../../assets/images/medicineBg.svg";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { useForm } from "react-hook-form";
import axios from "axios";
import { AuthContext } from "../../Contexts/authContext";
import { useNavigate } from "react-router";
import Cookies from 'js-cookie';

export default function Login() {

  const navigate = useNavigate();
  const { isAuth, setIsAuth } = useContext(AuthContext);

  useEffect(() => {
    if (isAuth) {
      return navigate('/home')
    } else {
      return navigate('/admin/login');
    }
  }, []);

  // LOGIN ---------------------------------------------------
  const { register, handleSubmit } = useForm();
  const onSubmit = handleSubmit( async (data) => {
    const response = await axios.post("http://localhost:8080/admin/signin", data);
    if (response.status == 200) {
      const {token} = response.data;
      Cookies.set('cis.validator', token, {
        expires: 1, //Expira em 1 dia
      });

      // Cookies.remove('cis.validator')

      setIsAuth(true);
      navigate('/home')
    } else {
      alert('Credenciais Inválidas')
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
                <AccountCircleIcon
                  sx={{ alignSelf: "center", fontSize: "60px" }}
                />
                <Typography variant="h5" color="initial" sx={{textAlign: "center"}}>
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
          </Box>
        
        </Box>
      </Container>
    </Fragment>
  );
}
