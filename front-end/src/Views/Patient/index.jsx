import { Container, Typography } from "@mui/material";
import React from 'react'

export default function Patients() {
  return(
    <React.Fragment>
      <Container maxWidth="lg">
      <Typography variant="h4" color="initial" sx={{margin: '1rem 0'}}>
          Listagem de Pacientes
        </Typography>
        <Typography variant="h5" color="initial">Pacientes funciona</Typography>
      </Container>
    </React.Fragment>
  );
}