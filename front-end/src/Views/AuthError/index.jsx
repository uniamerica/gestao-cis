import Auth from '../../assets/images/auth.png';
import React from "react";
import { Box,Typography, Button } from "@mui/material";
import { useNavigate } from "react-router";

export default function AuthError() {
  const navigate = useNavigate();

  function redirect(value) {
    switch(value) {
      case 'paciente':
        navigate('/login')
      break;
      case 'profissional':
        navigate('/admin/login')
      break;

    }
  }

  return (
    <React.Fragment>
      <Box sx={{
        height: '80vh', 
        width: '100vw',
        backgroundColor: '#FFF', 
        flexDirection: 'column', 
        justifyContent: 'center', 
        alignItems: 'center',
        display: 'flex',
        gap: '1rem',
        borderRadius: 1, 
        }}>
        <img src={Auth} width={750} height={500} />
        <Typography variant="h5" color="initial" sx={{textAlign: 'center'}}>Oops.. Parece que você não está logado</Typography>
        <Typography variant="h6" color="initial" sx={{textAlign: 'center'}}>Por favor realize novamente o login</Typography>
        <Box sx={{display: 'flex', gap: '1rem'}}>
          <Button variant="contained" onClick={() => redirect('paciente')}>
            Login Paciente
          </Button>
          <Button variant="contained" onClick={() => redirect('profissional')}>
            Login Colaborador
          </Button>
        </Box>


      </Box>
    </React.Fragment>
  )
}