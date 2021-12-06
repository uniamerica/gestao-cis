import React from "react";
import { Box,Button,Modal,Typography,TextField,Autocomplete,Container } from "@mui/material";
import { useNavigate } from "react-router";

export default function Footer() {
  const navigate = useNavigate();

  return(
    <React.Fragment>
      <Container maxWidth="lg">
        <Box sx={{height: 60, display: 'flex', justifyContent: 'center', alignItems: 'center',}}>
          <Button variant="text" sx={{ color: '#66b34d'}} onClick={() => navigate('/reclamacoes')}>
            Reclamações
          </Button>
        </Box>
      </Container>
    </React.Fragment>
  );
}