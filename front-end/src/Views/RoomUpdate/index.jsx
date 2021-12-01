import { useState, useEffect } from "react";
import { Fragment } from "react";
import {
  Box,
  Button,
  Typography,
  TextField,
  Autocomplete,
  Container,
  Grid,
} from "@mui/material";
import axios from "axios";

const buttonStyle = {
  marginTop: "2%",
  left: "75%",
  borderRadius: 20,
  backgroundColor: "#00939F",
  boxShadow: "none",
  textTransform: "none",
};

export default function Professional() {
  const [room, setRoom] = useState(
    (room = {
      id: 0,
      name: "",
      specialties: [],
    })
  );

  useEffect(() => {
    axios.get();
  }, []);
  return (
    <Container>
      <Grid container spacing={2}>
        <Grid item lg={12} md={12}>
          <Typography
            variant="h5"
            color="initial"
            textAlign="center"
            marginTop="30px"
          >
            Atualizar Sala <br />
          </Typography>
        </Grid>
        <Grid item xs={12}>
          <TextField
            required
            fullWidth
            type="text"
            id="outlined-required"
            label="Nome da Sala"
            sx={{ marginTop: "1.5rem" }}
          />
        </Grid>
        <Grid item xs={12}>
          <Autocomplete
            required
            sx={{ marginTop: "1.5rem" }}
            multiple
            id="tags-outlined"
            options={especialidades}
            getOptionLabel={(option) => option.name}
            filterSelectedOptions
            renderInput={(params) => (
              <TextField
                {...params}
                fullWidth
                label="Especialidades"
                placeholder="Especialidades"
              />
            )}
          />
        </Grid>

        <Grid item xs={12} display="flex" justifyContent="center">
          <Button
            type="submit"
            variant="contained"
            sx={{ marginTop: "2rem", backgroundColor: "#00939F" }}
          >
            Atualizar
          </Button>
        </Grid>
      </Grid>
    </Container>

    //   </Box>
    // </Box>
  );
}

const especialidades = [
  {
    id: 1,
    name: "Psicologia",
  },
  { id: 2, name: "Nutrição" },
  { id: 3, name: "Fisioterapia" },
  { id: 4, name: "Psiquiatria" },
  { id: 5, name: "Acupuntura" },
  { id: 6, name: "Massagem Terapêutica" },
  { id: 7, name: "Psicopedagogia" },
  { id: 8, name: "Fonoaudiologia" },
  { id: 9, name: "Estética facial e corporal" },
];
