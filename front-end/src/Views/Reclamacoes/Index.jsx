import React from "react";
import Milton from '../../assets/images/reclamacoes.jpg'
import { Box,Button,Modal,Typography,TextField,Autocomplete,Container } from "@mui/material";

export default function Reclamacoes() {
  return(
    <React.Fragment>
      <Container maxWidth="lg">
        <Box sx={{height: '80vh', display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
          <img src={Milton} alt="" />
        </Box>
      </Container>
    </React.Fragment>
  );
}